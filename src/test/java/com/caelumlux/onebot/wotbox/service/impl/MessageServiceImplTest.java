package com.caelumlux.onebot.wotbox.service.impl;

import cn.hutool.core.codec.Base64;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import com.ruiyun.jvppeteer.options.Viewport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Caelumlux
 * @ClassName MessageServiceImplTest
 * @Description
 * @Date 2024/8/8 16:58
 */
public class MessageServiceImplTest {


    @Test
    public void getLastFight() throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        String path = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withExecutablePath(path).build();
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(485);
        viewport.setHeight(2560);
        page.setViewport(viewport);
        Document document = Jsoup.connect("https://wotapp.ouj.com/index.php?r=wx/home&pn=卿云烂兮").get();
        Elements elementsByClass = document.getElementsByClass("record-item");
        if (!CollectionUtils.isEmpty(elementsByClass)) {
            Element element = elementsByClass.get(0);
            String attr = element.select("a").attr("href");
            String href = "https://wotapp.ouj.com/" + attr;
            page.goTo(href);
            ScreenshotOptions screenshotOptions = new ScreenshotOptions();
            //设置截图范围
            Clip clip = new Clip(1.0, 1.56, 480, 580);
            screenshotOptions.setClip(clip);
            //设置存放的路径
            String screenshot = page.screenshot(screenshotOptions);
            File file = new File("E:\\资料\\素材\\资料\\xxxx.png");
            Base64.decodeToFile(screenshot, file);
        } else {
            throw new Exception("最近一次战斗截图出错！");
        }
    }

}