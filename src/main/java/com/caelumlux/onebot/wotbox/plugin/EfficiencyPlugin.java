package com.caelumlux.onebot.wotbox.plugin;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import com.caelumlux.onebot.wotbox.service.MessageService;
import com.caelumlux.onebot.wotbox.service.WotUserService;
import com.mikuac.shiro.annotation.AnyMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.AnyMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

/**
 * @author Caelumlux
 * @ClassName EfficiencyPlugin
 * @Description
 * @Date 2024/2/27 15:53
 */
@Shiro
@Component
public class EfficiencyPlugin {

    @Autowired
    MessageService messageService;

    @Autowired
    WotUserService wotUserService;

    // 同时监听群组及私聊消息 并根据消息类型（私聊，群聊）回复
    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "^坦克绑定\s(.*)?$")
    public void bindUser(Bot bot, AnyMessageEvent event, Matcher matcher) {
        String username = matcher.group(1);
        Long userId = event.getUserId();
        WotUser wotUser = wotUserService.findByUserQq(userId);
        if (wotUser != null) {
            wotUser.setWotName(username);
            wotUserService.save(wotUser);
        } else {
            wotUser = new WotUser();
            wotUser.setUserQq(userId);
            wotUser.setWotName(username);
            wotUserService.save(wotUser);
        }
        String msg = "用户【" + username + "】绑定成功！";
        bot.sendMsg(event, msg, false);
    }

    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "^查询效率\s(.*)?$")
    public void find(Bot bot, AnyMessageEvent event, Matcher matcher) {
        String username = matcher.group(1);
        String msg = messageService.index(username);
        bot.sendMsg(event, msg, false);
    }

    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "我的效率")
    public void mine(Bot bot, AnyMessageEvent event, Matcher matcher) {
        WotUser byUserQq = wotUserService.findByUserQq(event.getUserId());
        if (byUserQq == null) {
            bot.sendMsg(event, "请先绑定坦克世界用户名！发送 【坦克绑定】+【用户名】完成绑定！示例： 坦克绑定 玩家123456", false);
        } else {
            String msg = messageService.index(byUserQq.getWotName());
            bot.sendMsg(event, msg, false);
        }
    }

}
