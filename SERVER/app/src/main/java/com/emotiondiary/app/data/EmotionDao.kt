package com.emotiondiary.app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmotionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: EmotionEntry)

    @Update
    suspend fun update(entry: EmotionEntry)

    @Delete
    suspend fun delete(entry: EmotionEntry)

    @Query("SELECT * FROM emotion_entries ORDER BY timestamp DESC LIMIT 10")
    fun getRecentEntries(): LiveData<List<EmotionEntry>>

    @Query("SELECT * FROM emotion_entries ORDER BY timestamp DESC")
    fun getAllEntries(): LiveData<List<EmotionEntry>>

    @Query("SELECT * FROM emotion_entries WHERE id = :id")
    suspend fun getEntryById(id: Long): EmotionEntry?

    @Query("SELECT AVG(mood) FROM emotion_entries WHERE timestamp >= :since")
    fun getAverageMood(since: Long): LiveData<Double?>

    @Query("SELECT AVG(mood) FROM emotion_entries WHERE timestamp >= :since AND timestamp <= :until")
    fun getAverageMoodBetween(since: Long, until: Long): LiveData<Double?>

    @Query("SELECT * FROM emotion_entries WHERE mood <= 3 ORDER BY timestamp DESC")
    fun getLowMoodEntries(): LiveData<List<EmotionEntry>>

    @Query("SELECT * FROM emotion_entries WHERE timestamp >= :since ORDER BY timestamp DESC")
    fun getEntriesSince(since: Long): LiveData<List<EmotionEntry>>

    @Query("SELECT COUNT(*) FROM emotion_entries WHERE mood <= 3 AND timestamp >= :since")
    fun getLowMoodCount(since: Long): LiveData<Int>

    @Query("DELETE FROM emotion_entries")
    suspend fun deleteAll()
}
