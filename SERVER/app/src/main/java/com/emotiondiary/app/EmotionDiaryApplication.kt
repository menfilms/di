package com.emotiondiary.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class EmotionDiaryApplication : Application() {

    companion object {
        const val CHANNEL_ID_DAILY_REMINDER = "daily_reminder_channel"
        const val CHANNEL_ID_SUPPORT = "support_channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val dailyChannel = NotificationChannel(
            CHANNEL_ID_DAILY_REMINDER,
            "Ежедневное напоминание",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Напоминание записать свои эмоции"
        }

        val supportChannel = NotificationChannel(
            CHANNEL_ID_SUPPORT,
            "Поддержка",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Сообщения поддержки при низком настроении"
            enableVibration(true)
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(dailyChannel)
        notificationManager.createNotificationChannel(supportChannel)
    }
}
