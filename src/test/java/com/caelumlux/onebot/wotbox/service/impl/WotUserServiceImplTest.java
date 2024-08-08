package com.caelumlux.onebot.wotbox.service.impl;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import com.caelumlux.onebot.wotbox.service.WotUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Caelumlux
 * @ClassName WotUserServiceImplTest
 * @Description
 * @Date 2024/8/8 11:21
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WotUserServiceImplTest {

    @Autowired
    private WotUserService wotUserService;

    @Test
    public void findByUserQq() {
        WotUser byUserQq = wotUserService.findByUserQq(1481150668L);
        System.out.println(byUserQq);
    }
}