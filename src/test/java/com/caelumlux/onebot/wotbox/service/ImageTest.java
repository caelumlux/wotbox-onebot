package com.caelumlux.onebot.wotbox.service;

import cn.hutool.core.codec.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author Caelumlux
 * @ClassName ImageTest
 * @Description
 * @Date 2024/8/8 17:40
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageTest {

    @Autowired
    MessageService service;

    @Test
    public void test() {
        String index = service.index("卿云烂兮");
        File file = new File("E:\\资料\\素材\\资料\\out.png");
        Base64.decodeToFile(index, file);
    }
}
