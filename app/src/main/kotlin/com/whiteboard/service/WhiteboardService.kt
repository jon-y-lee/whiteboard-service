package com.whiteboard.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.whiteboard.domain.Message
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class WhiteboardService(private val objectMapper: ObjectMapper) {

    val connectedSessions = mutableMapOf<String, List<WebSocketSession>?>()

    fun publishMessage(session: WebSocketSession, message: Message) {
        val boardIdSocketSessions = connectedSessions.get(message.boardId)

        boardIdSocketSessions?.forEach{ ses ->
            if (ses.isOpen && ses.id != session.id) {
                ses.sendMessage(TextMessage(objectMapper.writeValueAsString(message)));
            }
        }
    }
}