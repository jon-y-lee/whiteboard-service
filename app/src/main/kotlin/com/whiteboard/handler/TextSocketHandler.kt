package com.whiteboard.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.whiteboard.domain.Message
import com.whiteboard.service.WhiteboardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class TextSocketHandler(private val objectMapper: ObjectMapper,
                        private val whiteboardService: WhiteboardService): TextWebSocketHandler() {

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

        whiteboardService.publishMessage(session, deserializedMessage)
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
