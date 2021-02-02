package com.whiteboard.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.whiteboard.handler.TextSocketHandler
import org.springframework.stereotype.Component
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Component
@EnableWebSocket
class WebSocketConfig(private val objectMapper: ObjectMapper,
                      private val textSocketHandler: TextSocketHandler)
    : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(textSocketHandler, "/board/{id}/user/{userid}")
                .setAllowedOrigins("*");
    }
}