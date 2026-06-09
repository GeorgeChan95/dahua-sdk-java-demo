package com.csg.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName DemoApplication
 * @Description
 * @Author George
 * @Date 2026/6/8 14:22
 */
@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("demo启动成功");
    }
}
