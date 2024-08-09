package com.caelumlux.onebot.wotbox.service.impl;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import com.caelumlux.onebot.wotbox.model.MergeImage;
import com.caelumlux.onebot.wotbox.service.MessageService;
import com.caelumlux.onebot.wotbox.service.WotUserService;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

/**
 * @author Caelumlux
 * @ClassName MessageServiceImpl
 * @Description
 * @Date 2024/2/27 16:02
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    WotUserService wotUserService;

    @Autowired
    @Qualifier("homePage")
    private Page homePage;

    @Autowired
    @Qualifier("lastPage")
    private Page lastPage;

    @Autowired
    @Qualifier("battleDataPage")
    private Page battleDataPage;

    @Override
    public String index(String username) {
        String msg;
        MergeImage image = new MergeImage();
        try {
            getHome(username, image);
            getLastFight(username, image);
            getBatlleData(username, image);
            String imagedMerge = mergeImage(image);
            msg = MsgUtils.builder().img("base64://" + imagedMerge).build();
        } catch (Exception e) {
            msg = MsgUtils.builder().text("访问出错！" + e.getMessage()).build();
        }
        return msg;
    }

    private void getHome(String username, MergeImage image) throws Exception {
        String homeUrl = "https://wotapp.ouj.com/index.php?r=wx/home&pn=" + username;
        //设置截图范围
        homePage.goTo(homeUrl);
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        Clip clip = new Clip(1.0, 1.56, 480, 1384);
        screenshotOptions.setClip(clip);
        //设置存放的路径
        String screenshot = homePage.screenshot(screenshotOptions);
        image.setLeftImage(screenshot);
    }

    private void getLastFight(String username, MergeImage image) throws Exception {
        String homeUrl = "https://wotapp.ouj.com/index.php?r=wx/home&pn=" + username;
        Document document = Jsoup.connect(homeUrl).get();
        Elements elementsByClass = document.getElementsByClass("record-item");
        if (!CollectionUtils.isEmpty(elementsByClass)) {
            Element element = elementsByClass.get(0);
            String attr = element.select("a").attr("href");
            String href = "https://wotapp.ouj.com/" + attr;
            lastPage.goTo(href);
            ScreenshotOptions screenshotOptions = new ScreenshotOptions();
            //设置截图范围
            Clip clip = new Clip(1.0, 1.56, 480, 580);
            screenshotOptions.setClip(clip);
            //设置存放的路径
            String screenshot = lastPage.screenshot(screenshotOptions);
            image.setRightMiddleImage(screenshot);
        } else {
            throw new Exception("最近一次战斗截图出错！");
        }
        Elements num = document.getElementsByClass("fight-num");
        if (!CollectionUtils.isEmpty(num)) {
            String fightNum = num.get(0).text();
            List<WotUser> byWotName = wotUserService.findByWotName(username);
            if (!CollectionUtils.isEmpty(byWotName)) {
                for (int i = 0; i < byWotName.size(); i++) {
                    WotUser wotUser = byWotName.get(i);
                    wotUser.setFightNum(Integer.parseInt(fightNum));
                    wotUserService.save(wotUser);
                }
            }
        } else {
            throw new Exception("最近一次战斗力！出错");
        }
    }

    private void getBatlleData(String username, MergeImage image) throws Exception {
        String batlleDataUrl = "https://wotapp.ouj.com/index.php?r=wx/BattleData&pn=" + username;
        battleDataPage.goTo(batlleDataUrl);
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clipTop = new Clip(1.0, 180, 480, 256);
        screenshotOptions.setClip(clipTop);
        String top = battleDataPage.screenshot(screenshotOptions);
        image.setRightTopImage(top);

        //设置截图范围
        Clip clipBottom = new Clip(1.0, 980, 480, 550);
        screenshotOptions.setClip(clipBottom);
        String bottom = battleDataPage.screenshot(screenshotOptions);
        image.setRightBottomImage(bottom);
    }

    private String mergeImage(MergeImage image) throws Exception {
        BufferedImage leftBufImage = base64ToBufferedImage(image.getLeftImage());
        BufferedImage rightTopImage = base64ToBufferedImage(image.getRightTopImage());
        BufferedImage rightMiddleImage = base64ToBufferedImage(image.getRightMiddleImage());
        BufferedImage rightBottomImage = base64ToBufferedImage(image.getRightBottomImage());
        int connImageWidth = leftBufImage.getWidth() * 2;
        int connImageHeight = leftBufImage.getHeight();

        BufferedImage connImage = new BufferedImage(connImageWidth, connImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics connGraphics = connImage.getGraphics();
        //第一张图左上角坐标为(0, 0)
        connGraphics.drawImage(leftBufImage, 0, 0, null);
        //第二张图左上角坐标为(leftBufImage.getWidth(), 0),画在第一张图的右边
        connGraphics.drawImage(rightTopImage, leftBufImage.getWidth(), 0, null);
        connGraphics.drawImage(rightMiddleImage, leftBufImage.getWidth(), rightTopImage.getHeight(), null);
        connGraphics.drawImage(rightBottomImage, leftBufImage.getWidth(), rightTopImage.getHeight() + rightMiddleImage.getHeight(), null);
        connGraphics.dispose();

        ByteArrayOutputStream bao = new ByteArrayOutputStream();//io流
        ImageIO.write(connImage, "png", bao);//写入流中

        byte[] bytes = Base64.getMimeEncoder().encode(bao.toByteArray());
        String png = new String(bytes);
        return png;
    }

    /**
     * base64 编码转换为 BufferedImage
     *
     * @param base64
     * @return
     */
    private BufferedImage base64ToBufferedImage(String base64) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] byteArray = decoder.decode(base64);
        ByteArrayInputStream bai = new ByteArrayInputStream(byteArray);
        return ImageIO.read(bai);

    }
}
