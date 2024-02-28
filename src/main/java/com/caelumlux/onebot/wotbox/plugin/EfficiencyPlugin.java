package com.caelumlux.onebot.wotbox.plugin;

import com.caelumlux.onebot.wotbox.service.MessageService;
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

    // 同时监听群组及私聊消息 并根据消息类型（私聊，群聊）回复
    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "^盒子效率\s(.*)?$")
    public void index(Bot bot, AnyMessageEvent event, Matcher matcher) {
        String username = matcher.group(1);
        String msg = messageService.index(username);
        bot.sendMsg(event, msg, false);
    }

    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "^战斗日志\s(.*)?$")
    public void battleLog(Bot bot, AnyMessageEvent event, Matcher matcher) {
        String username = matcher.group(1);
        String msg = messageService.battleLog(username);
        bot.sendMsg(event, msg, false);
    }

    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "^总战绩\s(.*)?$")
    public void battlesStat(Bot bot, AnyMessageEvent event, Matcher matcher) {
        String username = matcher.group(1);
        String msg = messageService.battleStat(username);
        bot.sendMsg(event, msg, false);
    }
}
