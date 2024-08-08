package com.caelumlux.onebot.wotbox.service;

import cn.hutool.core.codec.Base64;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import com.ruiyun.jvppeteer.options.Viewport;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Caelumlux
 * @ClassName BotHtmlServiceTest
 * @Description
 * @Date 2024/2/27 16:28
 */
public class JvppeteerTest {

    @Test
    public void screenshotTest() throws Exception {
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
        page.goTo("https://wotapp.ouj.com/index.php?r=wx/home&pn=卿云烂兮");
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 480, 1384);
        screenshotOptions.setClip(clip);
        //设置存放的路径
        String screenshot = page.screenshot(screenshotOptions);
        File file = new File("E:\\资料\\素材\\资料\\素材test.png");
        Base64.decodeToFile(screenshot, file);
        System.out.println(screenshot);
    }

    @Test
    public void screenshotTestLast() throws Exception {
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
        page.goTo("https://wotapp.ouj.com/index.php?r=wx/arenaDetail&arena_id=123456822306911655&pn=卿云烂兮");
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 480, 580);
        screenshotOptions.setClip(clip);
        //设置存放的路径
        String screenshot = page.screenshot(screenshotOptions);
        File file = new File("E:\\资料\\素材\\资料\\素材test2.png");
        Base64.decodeToFile(screenshot, file);
        System.out.println(screenshot);
    }

    @Test
    public void screenshotAbility() throws Exception {
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
        page.goTo("https://wotapp.ouj.com/index.php?r=wx/BattleData&pn=&pn=卿云烂兮");
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 180, 480, 256);
        screenshotOptions.setClip(clip);
        //设置存放的路径
        String screenshot = page.screenshot(screenshotOptions);
        File file = new File("E:\\资料\\素材\\资料\\素材test3.png");
        Base64.decodeToFile(screenshot, file);

        //设置截图范围
        Clip clip2 = new Clip(1.0, 980, 480, 550);
        screenshotOptions.setClip(clip2);
        //设置存放的路径
        String screenshot2 = page.screenshot(screenshotOptions);
        File file2 = new File("E:\\资料\\素材\\资料\\素材test4.png");
        Base64.decodeToFile(screenshot2, file2);
    }

    @Test
    public void imageMerge() throws IOException {
        BufferedImage leftBufImage = ImageIO.read(new File("E:\\资料\\素材\\资料\\素材test.png"));
        BufferedImage rightTopImage = ImageIO.read(new File("E:\\资料\\素材\\资料\\素材test3.png"));
        BufferedImage rightMiddleImage = ImageIO.read(new File("E:\\资料\\素材\\资料\\素材test2.png"));
        BufferedImage rightBottomImage = ImageIO.read(new File("E:\\资料\\素材\\资料\\素材test4.png"));

        String targetImagePath = "E:\\资料\\素材\\资料\\素材mergeImage.png";

        int connImageWidth = leftBufImage.getWidth() * 2;
        int connImageHeight = leftBufImage.getHeight();

        BufferedImage connImage = new BufferedImage(connImageWidth, connImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics connGraphics = connImage.getGraphics();
        //第一张图左上角坐标为(0, 0)
        connGraphics.drawImage(leftBufImage, 0, 0, null);
        //第二张图左上角坐标为(leftBufImage.getWidth(), 0),画在第一张图的右边
        connGraphics.drawImage(rightTopImage, leftBufImage.getWidth(), 0, null);
        connGraphics.drawImage(rightMiddleImage, leftBufImage.getWidth(), rightTopImage.getHeight(), null);
        connGraphics.drawImage(rightBottomImage, leftBufImage.getWidth(), rightTopImage.getHeight()+rightMiddleImage.getHeight(), null);
        connGraphics.dispose();
        ImageIO.write(connImage, "PNG", new File(targetImagePath));

    }
}