package com.caelumlux.onebot.wotbox.service;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.*;
import org.junit.jupiter.api.Test;

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
        viewport.setWidth(900);
        viewport.setHeight(600);
        page.setViewport(viewport);
        page.goTo("https://wotbox.ouj.com/wotbox/index.php?r=default%2Findex&pn=卿云烂兮");
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 816, 578);
        screenshotOptions.setClip(clip);
        //设置存放的路径
        String screenshot = page.screenshot(screenshotOptions);
        System.out.println("------------------------------------------");
        System.out.println(screenshot);
        System.out.println("------------------------------------------");
    }

}