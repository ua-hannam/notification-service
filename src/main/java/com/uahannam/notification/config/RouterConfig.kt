package com.uahannam.notification.config

import com.uahannam.notification.handler.NotificationHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig {

    @Bean
    fun routerFunction(notificationHandler: NotificationHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/api/push/test", notificationHandler::testNotification)
        }
    }
}