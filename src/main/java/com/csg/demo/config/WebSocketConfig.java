package com.csg.demo.config;

import com.csg.demo.websocket.DahuaTalkWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket 配置。
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final DahuaTalkWebSocketHandler dahuaTalkWebSocketHandler;

    public WebSocketConfig(DahuaTalkWebSocketHandler dahuaTalkWebSocketHandler) {
        this.dahuaTalkWebSocketHandler = dahuaTalkWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dahuaTalkWebSocketHandler, "/dahua/ws/talk")
                .setAllowedOrigins("*");
    }

    /**
     * 显式声明名为 taskScheduler 的调度器。
     * 解决 @EnableScheduling 与 @EnableWebSocket 共存时，
     * ScheduledAnnotationBeanPostProcessor 误命中 SockJS 的 NullBean 导致启动失败的问题，
     * 同时供 @Scheduled 任务复用。
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        scheduler.setThreadNamePrefix("dahua-scheduler-");
        scheduler.initialize();
        return scheduler;
    }
}
