package com.whiteboard.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.whiteboard.domain.Message
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


class TextSocketHandler(private val objectMapper: ObjectMapper): TextWebSocketHandler() {

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        var deserializedMessage: Message;
        try {
            deserializedMessage = objectMapper.readValue<Message>(message.payload)
        } catch (ex: Exception) {
            println("Exception found in ex:$ex");
            return;
        }

        println(message = "Handled New Text Message:" + deserializedMessage.payload)
    }

    @Throws(java.lang.Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession?, status: CloseStatus?) {
        println("After Connection Closed")
    }

    @Throws(java.lang.Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession?) {
        println("Connect Established")
    }

    @Throws(java.lang.Exception::class)
    override fun handleTransportError(session: WebSocketSession?, exception: Throwable?) {
        println("Transport Error");
        TODO("Need to handle this situation")
    }


}
