package com.emotiondiary.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.emotiondiary.app.notifications.NotificationHelper

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationHelper = NotificationHelper(context)

        when (intent.action) {
            NotificationHelper.ACTION_DAILY_REMINDER -> {
                notificationHelper.showDailyReminderNotification()
            }
            NotificationHelper.ACTION_SUPPORT -> {
                notificationHelper.showSupportNotification()
            }
        }
    }
}
