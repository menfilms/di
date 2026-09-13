package com.emotiondiary.app

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emotiondiary.app.data.EmotionEntry
import com.emotiondiary.app.databinding.ActivityAddEntryBinding
import com.emotiondiary.app.viewmodels.EmotionViewModel
import java.util.Date

class AddEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEntryBinding
    private lateinit var viewModel: EmotionViewModel
    private var currentMood: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmotionViewModel::class.java]

        setupToolbar()
        setupSeekBar()
        setupMoodButtons()
        setupSaveButton()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Новая запись"
    }

    private fun setupSeekBar() {
        binding.seekBarMood.apply {
            max = 9 // 0-9, но значение будет 1-10
            progress = 4 // default 5
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    currentMood = progress + 1
                    updateMoodDisplay()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
        updateMoodDisplay()
    }

    private fun setupMoodButtons() {
        binding.btnMood1.setOnClickListener { setMood(1) }
        binding.btnMood2.setOnClickListener { setMood(2) }
        binding.btnMood3.setOnClickListener { setMood(3) }
        binding.btnMood4.setOnClickListener { setMood(4) }
        binding.btnMood5.setOnClickListener { setMood(5) }
        binding.btnMood6.setOnClickListener { setMood(6) }
        binding.btnMood7.setOnClickListener { setMood(7) }
        binding.btnMood8.setOnClickListener { setMood(8) }
        binding.btnMood9.setOnClickListener { setMood(9) }
        binding.btnMood10.setOnClickListener { setMood(10) }
    }

    private fun setMood(value: Int) {
        currentMood = value
        binding.seekBarMood.progress = value - 1
        updateMoodDisplay()
    }

    private fun updateMoodDisplay() {
        val emoji = when {
            currentMood >= 9 -> "😄"
            currentMood >= 7 -> "😊"
            currentMood >= 5 -> "🙂"
            currentMood >= 3 -> "😐"
            currentMood >= 2 -> "😟"
            else -> "😢"
        }
        binding.tvMoodValue.text = "$currentMood $emoji"
        binding.tvMoodLabel.text = getMoodLabel(currentMood)
    }

    private fun getMoodLabel(mood: Int): String {
        return when {
            mood >= 9 -> "Отлично!"
            mood >= 7 -> "Хорошо"
            mood >= 5 -> "Нормально"
            mood >= 3 -> "Плоховато"
            mood >= 2 -> "Плохо"
            else -> "Очень плохо"
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val description = binding.etDescription.text.toString().trim()

            if (description.isEmpty()) {
                binding.tilDescription.error = "Опишите своё настроение"
                return@setOnClickListener
            }

            binding.tilDescription.error = null

            val entry = EmotionEntry(
                mood = currentMood,
                description = description,
                timestamp = Date(),
                tags = getSelectedTags()
            )

            viewModel.insertEntry(entry)

            Toast.makeText(this, "Запись сохранена ✓", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getSelectedTags(): String {
        val tags = mutableListOf<String>()
        if (binding.chipWork.isChecked) tags.add("работа")
        if (binding.chipFamily.isChecked) tags.add("семья")
        if (binding.chipHealth.isChecked) tags.add("здоровье")
        if (binding.chipFriends.isChecked) tags.add("друзья")
        if (binding.chipRest.isChecked) tags.add("отдых")
        if (binding.chipStudy.isChecked) tags.add("учёба")
        return tags.joinToString(",")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
