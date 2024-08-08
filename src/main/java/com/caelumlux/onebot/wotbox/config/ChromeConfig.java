package com.caelumlux.onebot.wotbox.config;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.Viewport;
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
    public Page homePage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(485);
        viewport.setHeight(2560);
        page.setViewport(viewport);
        return page;
    }

    @Bean
    public Page lastPage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(485);
        viewport.setHeight(2560);
        page.setViewport(viewport);
        return page;
    }

    @Bean
    public Page battleDataPage(Browser browser) throws Exception {
        Page page = browser.newPage();
        Viewport viewport = new Viewport();
        viewport.setWidth(485);
        viewport.setHeight(2560);
        page.setViewport(viewport);
        return page;
    }

}
