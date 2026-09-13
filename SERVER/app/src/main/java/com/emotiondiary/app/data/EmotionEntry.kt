package com.emotiondiary.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "emotion_entries")
data class EmotionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mood: Int,           // 1-10
    val description: String,
    val timestamp: Date,
    val tags: String = ""    // comma-separated tags
) {
    fun getMoodEmoji(): String {
        return when {
            mood >= 9 -> "😄"
            mood >= 7 -> "😊"
            mood >= 5 -> "🙂"
            mood >= 3 -> "😐"
            mood >= 2 -> "😟"
            else -> "😢"
        }
    }

    fun getMoodColor(): Int {
        return when {
            mood >= 9 -> 0xFF4CAF50.toInt()  // Green
            mood >= 7 -> 0xFF8BC34A.toInt()  // Light Green
            mood >= 5 -> 0xFFFFEB3B.toInt()  // Yellow
            mood >= 3 -> 0xFFFF9800.toInt()  // Orange
            mood >= 2 -> 0xFFFF5722.toInt()  // Deep Orange
            else -> 0xFFF44336.toInt()       // Red
        }
    }

    fun isLowMood(): Boolean = mood <= 3
}
