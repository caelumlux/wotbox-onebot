package com.caelumlux.onebot.wotbox.service.impl;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import com.caelumlux.onebot.wotbox.repositoy.WotUserRepository;
import com.caelumlux.onebot.wotbox.service.WotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Caelumlux
 * @ClassName WotUserServiceImpl
 * @Description
 * @Date 2024/8/8 11:20
 */
@Service
public class WotUserServiceImpl implements WotUserService {
    @Autowired
    private WotUserRepository wotUserRepository;

    @Override
    public WotUser findByUserQq(Long userQq) {
        return wotUserRepository.findByUserQq(userQq);
    }

    @Override
    public List<WotUser> findByWotName(String userName) {
        return wotUserRepository.findByWotName(userName);
    }

    @Override
    public void save(WotUser wotUser) {
        wotUserRepository.save(wotUser);
    }
}
