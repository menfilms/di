package com.emotiondiary.app.data

import androidx.lifecycle.LiveData

class EmotionRepository(private val emotionDao: EmotionDao) {

    val recentEntries: LiveData<List<EmotionEntry>> = emotionDao.getRecentEntries()
    val allEntries: LiveData<List<EmotionEntry>> = emotionDao.getAllEntries()
    val lowMoodEntries: LiveData<List<EmotionEntry>> = emotionDao.getLowMoodEntries()

    suspend fun insert(entry: EmotionEntry) {
        emotionDao.insert(entry)
    }

    suspend fun update(entry: EmotionEntry) {
        emotionDao.update(entry)
    }

    suspend fun delete(entry: EmotionEntry) {
        emotionDao.delete(entry)
    }

    suspend fun getEntryById(id: Long): EmotionEntry? {
        return emotionDao.getEntryById(id)
    }

    fun getAverageMood(since: Long): LiveData<Double?> {
        return emotionDao.getAverageMood(since)
    }

    fun getLowMoodCount(since: Long): LiveData<Int> {
        return emotionDao.getLowMoodCount(since)
    }

    suspend fun deleteAll() {
        emotionDao.deleteAll()
    }
}
