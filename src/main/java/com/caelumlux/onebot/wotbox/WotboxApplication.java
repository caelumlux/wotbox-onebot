package com.caelumlux.onebot.wotbox;

import com.caelumlux.onebot.wotbox.config.BaseDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Caelumlux
 * @ClassName Application
 * @Description
 * @Date 2024/2/27 15:43
 */
@SpringBootApplication
@EntityScan("com.caelumlux.onebot.wotbox.entity")
@EnableJpaRepositories(basePackages = "com.caelumlux.onebot.wotbox.repositoy",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = BaseDao.class))
public class WotboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WotboxApplication.class, args);
    }
}
