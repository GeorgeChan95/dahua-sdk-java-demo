package com.csg.demo.config;

import com.csg.demo.websocket.DahuaTalkWebSocketHandler;
import org.springframework.context.annotation.Configuration;
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
}
