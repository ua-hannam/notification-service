package com.uahannam.notification.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class PushService(
    private val firebaseMessaging: FirebaseMessaging
) {


    fun sendTestPush(): String =
        firebaseMessaging.send(Message.builder()
            .setToken("eDhKyuWKlAn_a4N8E3urlK:APA91bFOCsFM6kkBaKvjFV7WECUJuv3SK27s-H_rfIWyrzBg2wuCCBL6xtlISdT-KtmWTcIStGXreD4moDVX625dQPD6Re0N1vahS_gCod8WbrxCOJQJjy9IwWqvf7zszT_g0wRoXJgZ")
            .setNotification(Notification.builder()
                .setBody("배달 실패")
                .setTitle("배달 망했는데 이래도 안 봄?")
                .build())
            .build())
}