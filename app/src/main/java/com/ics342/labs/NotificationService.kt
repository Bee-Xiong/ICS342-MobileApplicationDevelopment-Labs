package com.ics342.labs

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class NotificationService: Service() {

    private val notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(this)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // If permission has not been granted, stop the service and return from
        // onStartCommand
        if (ContextCompat.checkSelfPermission(
                this,
                permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return START_NOT_STICKY
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.star)
            .setContentTitle("My notification")
            .setContentText("Hello World")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)){
                notificationManager.notify(NOTIFICATION_ID, builder.build())
            }
        return START_STICKY_COMPATIBILITY
    }





    override fun onBind(intent: Intent?): IBinder? {
        // No need to implement for lab 8
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "LAB_7_CHANNEL_ID"
        private const val NOTIFICATION_ID = 1234
    }
}
