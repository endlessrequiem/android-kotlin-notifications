package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private val TAG = "MyFirebaseMessagingService".substring(0, 23)

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Step 3.5 check messages for data
        remoteMessage.data.let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        // Step 3.6 check messages for notification and call sendNotification
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")

        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        //TODO("Not yet implemented")
    }
}