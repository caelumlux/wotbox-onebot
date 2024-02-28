package com.caelumlux.onebot.wotbox.config;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Caelumlux
 * @ClassName ChromeConfig
 * @Description
 * @Date 2024/2/28 14:53
 */
@Configuration
public class ChromeConfig {

    @Value("${shiro.chrome.path}")
    private String path;

    @Bean
    public Browser browser() throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withExecutablePath(path).build();
        return Puppeteer.launch(options);
    }

    @Bean
    public Page indexPage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(900);
        viewport.setHeight(600);
        page.setViewport(viewport);
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 816, 578);
        screenshotOptions.setClip(clip);
        return page;
    }

    @Bean
    public Page battleLogPage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(900);
        viewport.setHeight(600);
        page.setViewport(viewport);
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 816, 578);
        screenshotOptions.setClip(clip);
        return page;
    }

    @Bean
    public Page battleStatPage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(900);
        viewport.setHeight(600);
        page.setViewport(viewport);
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        //设置截图范围
        Clip clip = new Clip(1.0, 1.56, 816, 578);
        screenshotOptions.setClip(clip);
        return page;
    }
}
