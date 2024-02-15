package com.example.posts.presentation.foreground_service

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.posts.R
import com.example.posts.presentation.MainActivity
import com.google.android.datatransport.runtime.logging.Logging.d
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FireBaseMessageService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        d("firebaseMessage", message.data.toString())
        if (message.data.isNotEmpty()) {
            val id = message.data["id"] ?: ""
            val title = message.data["title"] ?: ""
            val desc = message.data["desc"] ?: ""
            notification(title, desc)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    private fun notification( title: String, desc: String) {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(applicationContext, "running_channel")
            .setContentTitle(title)
            .setContentText(desc)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_comment)
            .build()

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(applicationContext)
                .notify(1, notification)
        }
    }


}