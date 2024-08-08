package com.caelumlux.onebot.wotbox.service;

import com.caelumlux.onebot.wotbox.entity.WotUser;

/**
 * @author Caelumlux
 * @ClassName WotUserService
 * @Description
 * @Date 2024/8/8 11:20
 */
public interface WotUserService {

    WotUser findByUserQq(Long userQq);

    void save(WotUser wotUser);
}
