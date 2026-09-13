package com.emotiondiary.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emotiondiary.app.databinding.ActivitySettingsBinding
import com.emotiondiary.app.notifications.NotificationHelper

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var notificationHelper: NotificationHelper

    companion object {
        private const val NOTIFICATION_PERMISSION_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationHelper = NotificationHelper(this)

        setupToolbar()
        loadSettings()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Настройки"
    }

    private fun loadSettings() {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        binding.switchNotifications.isChecked = prefs.getBoolean("notifications_enabled", true)
        binding.switchSupportNotifications.isChecked = prefs.getBoolean("support_enabled", true)

        val hour = prefs.getInt("reminder_hour", 20)
        val minute = prefs.getInt("reminder_minute", 0)
        binding.timePickerReminder.setIs24HourView(true)
        binding.timePickerReminder.hour = hour
        binding.timePickerReminder.minute = minute
    }

    private fun setupListeners() {
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("notifications_enabled", isChecked)
            if (isChecked) {
                requestNotificationPermission()
                val hour = binding.timePickerReminder.hour
                val minute = binding.timePickerReminder.minute
                notificationHelper.scheduleDailyReminder(hour, minute)
            } else {
                notificationHelper.cancelDailyReminder()
            }
        }

        binding.switchSupportNotifications.setOnCheckedChangeListener { _, isChecked ->
            saveSetting("support_enabled", isChecked)
            if (isChecked) {
                requestNotificationPermission()
            }
        }

        binding.timePickerReminder.setOnTimeChangedListener { _, hour, minute ->
            saveSetting("reminder_hour", hour)
            saveSetting("reminder_minute", minute)
            if (binding.switchNotifications.isChecked) {
                notificationHelper.scheduleDailyReminder(hour, minute)
            }
        }

        binding.btnTestSupport.setOnClickListener {
            notificationHelper.showSupportNotification()
            Toast.makeText(this, "Тестовое уведомление отправлено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveSetting(key: String, value: Any) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        with(prefs.edit()) {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is String -> putString(key, value)
            }
            apply()
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Уведомления разрешены ✓", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Уведомления отклонены", Toast.LENGTH_SHORT).show()
                binding.switchNotifications.isChecked = false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
