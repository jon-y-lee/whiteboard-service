package com.whiteboard.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler

//import com.fasterxml.jackson.module.kotlin.KotlinModule

@Configuration
class WhiteboardConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }

    @Bean
    fun webSocketHandler(): WebSocketHandler {
        return TextSocketHandler(objectMapper())
    }

}