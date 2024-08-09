package com.caelumlux.onebot.wotbox.service;

import com.caelumlux.onebot.wotbox.entity.WotUser;

import java.util.List;

/**
 * @author Caelumlux
 * @ClassName WotUserService
 * @Description
 * @Date 2024/8/8 11:20
 */
public interface WotUserService {

    WotUser findByUserQq(Long userQq);

    List<WotUser> findByWotName(String userName);

    void save(WotUser wotUser);
}
