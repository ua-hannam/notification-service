package com.uahannam.notification.controller

import com.uahannam.notification.service.PushService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(
    private val pushService: PushService
) {

    @GetMapping("/api/push/test")
    fun testNotification() : ResponseEntity<HttpStatus> {
        pushService.sendTestPush()
        return ResponseEntity.ok().build()
    }
}