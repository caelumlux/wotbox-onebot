package com.caelumlux.onebot.wotbox.service;

import com.mikuac.shiro.common.utils.MsgUtils;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.Clip;
import com.ruiyun.jvppeteer.options.ScreenshotOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Caelumlux
 * @ClassName MessageServiceImpl
 * @Description
 * @Date 2024/2/27 16:02
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("indexPage")
    private Page indexPage;

    @Autowired
    @Qualifier("battleLogPage")
    private Page battleLogPage;

    @Autowired
    @Qualifier("battleStatPage")
    private Page battlesStatPage;


    @Override
    public String index(String username) {
        String url = "https://wotbox.ouj.com/wotbox/index.php?r=default%2Findex&pn=" + username;
        return screenShot(indexPage, url);
    }

    @Override
    public String battleLog(String username) {
        String url = "https://wotbox.ouj.com/wotbox/index.php?r=default/battlelog&p=1&pn=" + username;
        return screenShot(battleLogPage, url);
    }


    @Override
    public String battleStat(String username) {
        String url = "https://wotbox.ouj.com/wotbox/index.php?r=default/battlestat&pn=" + username;
        return screenShot(battlesStatPage, url);
    }


    private String screenShot(Page page, String url) {
        String msg = "";
        try {
            page.goTo(url);
            ScreenshotOptions screenshotOptions = new ScreenshotOptions();
            //设置截图范围
            Clip clip = new Clip(1.0, 1.56, 816, 578);
            screenshotOptions.setClip(clip);
            String screenshot = page.screenshot(screenshotOptions);
            msg = MsgUtils.builder().img(screenshot).build();
        } catch (Exception e) {
            msg = MsgUtils.builder().text("访问出错！" + e.getMessage()).build();
        }
        return msg;
    }
}
