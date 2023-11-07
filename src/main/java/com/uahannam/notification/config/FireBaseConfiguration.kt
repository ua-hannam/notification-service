package com.uahannam.notification.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FireBaseConfiguration {


    @Bean
    fun firebaseMessaging() : FirebaseMessaging {
        val googleCredentials =
                GoogleCredentials.fromStream(ClassPathResource("firebase/uahannam-d2649-firebase-adminsdk-dg9me-fff99ba964.json").inputStream)
                        .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))

        val secondaryAppConfig = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build()

        return FirebaseMessaging.getInstance(FirebaseApp.initializeApp(secondaryAppConfig))
    }
}