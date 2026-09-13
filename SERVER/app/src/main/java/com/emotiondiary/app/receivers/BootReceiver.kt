package com.emotiondiary.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.emotiondiary.app.notifications.NotificationHelper

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Пересоздаём напоминания после перезагрузки
            val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val notificationsEnabled = prefs.getBoolean("notifications_enabled", true)
            val reminderHour = prefs.getInt("reminder_hour", 20)
            val reminderMinute = prefs.getInt("reminder_minute", 0)

            if (notificationsEnabled) {
                val notificationHelper = NotificationHelper(context)
                notificationHelper.scheduleDailyReminder(reminderHour, reminderMinute)
            }
        }
    }
}
