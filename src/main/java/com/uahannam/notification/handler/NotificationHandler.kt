package com.uahannam.notification.handler

import com.uahannam.notification.service.PushService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class NotificationHandler(
    private val pushService: PushService
) {

    fun testNotification(serverRequest: ServerRequest) : Mono<ServerResponse> {
        pushService.sendTestPush()
        return ServerResponse.ok().build()
    }
}