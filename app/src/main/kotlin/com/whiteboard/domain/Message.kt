package com.whiteboard.domain

data class Message(
        val boardId: String?,
        val userName: String?,
        val attendees: Set<String>?,
        val payload: String)