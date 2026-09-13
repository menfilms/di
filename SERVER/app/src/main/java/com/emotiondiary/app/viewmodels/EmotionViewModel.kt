package com.emotiondiary.app.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.emotiondiary.app.data.AppDatabase
import com.emotiondiary.app.data.EmotionEntry
import com.emotiondiary.app.data.EmotionRepository
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class EmotionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmotionRepository
    val recentEntries: LiveData<List<EmotionEntry>>
    val allEntries: LiveData<List<EmotionEntry>>
    val averageMood: LiveData<Double?>
    val lowMoodCount: LiveData<Int>

    private val _showSupport = MutableLiveData<Boolean>()
    val showSupport: LiveData<Boolean> = _showSupport

    init {
        val dao = AppDatabase.getDatabase(application).emotionDao()
        repository = EmotionRepository(dao)
        recentEntries = repository.recentEntries
        allEntries = repository.allEntries

        val weekAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7)
        averageMood = repository.getAverageMood(weekAgo)
        lowMoodCount = repository.getLowMoodCount(weekAgo)
    }

    fun insertEntry(entry: EmotionEntry) = viewModelScope.launch {
        repository.insert(entry)
        checkMoodLevel(entry.mood)
    }

    fun updateEntry(entry: EmotionEntry) = viewModelScope.launch {
        repository.update(entry)
    }

    fun deleteEntry(entry: EmotionEntry) = viewModelScope.launch {
        repository.delete(entry)
    }

    fun loadRecentEntries() {
        // Данные обновляются автоматически через LiveData
    }

    private fun checkMoodLevel(mood: Int) {
        if (mood <= 3) {
            _showSupport.value = true
        }
    }

    fun supportShown() {
        _showSupport.value = false
    }

    fun deleteAllEntries() = viewModelScope.launch {
        repository.deleteAll()
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmotionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EmotionViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
