package com.whiteboard.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WhiteboardController {

    @PostMapping(path = ["/create"],
            produces = ["application/json"],
            consumes = ["application/json"])
    @CrossOrigin
    fun createWhiteboard() {
        println("Creating whiteboard:");
    }
}