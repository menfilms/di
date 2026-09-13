package com.emotiondiary.app

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotiondiary.app.adapters.EmotionAdapter
import com.emotiondiary.app.databinding.ActivityMainBinding
import com.emotiondiary.app.viewmodels.EmotionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EmotionViewModel
    private lateinit var adapter: EmotionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmotionViewModel::class.java]

        setupRecyclerView()
        setupButtons()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = EmotionAdapter { entry ->
            // Обработка нажатия на запись
            showEntryDetails(entry)
        }
        binding.recyclerViewEmotions.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupButtons() {
        binding.fabAddEntry.setOnClickListener {
            startActivity(Intent(this, AddEntryActivity::class.java))
        }

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.recentEntries.observe(this) { entries ->
            adapter.submitList(entries)
            if (entries.isEmpty()) {
                binding.tvEmptyState.visibility = android.view.View.VISIBLE
                binding.recyclerViewEmotions.visibility = android.view.View.GONE
            } else {
                binding.tvEmptyState.visibility = android.view.View.GONE
                binding.recyclerViewEmotions.visibility = android.view.View.VISIBLE
            }
        }

        viewModel.averageMood.observe(this) { average ->
            if (average != null) {
                binding.tvAverageMood.text = String.format("Среднее настроение: %.1f", average)
                updateMoodIndicator(average)
            } else {
                binding.tvAverageMood.text = "Нет данных"
            }
        }
    }

    private fun updateMoodIndicator(mood: Double) {
        val emoji = when {
            mood >= 8 -> "😊"
            mood >= 6 -> "🙂"
            mood >= 4 -> "😐"
            mood >= 2 -> "😟"
            else -> "😢"
        }
        binding.tvMoodEmoji.text = emoji
    }

    private fun showEntryDetails(entry: com.emotiondiary.app.data.EmotionEntry) {
        val dialog = SupportDialogFragment.newInstance(entry)
        dialog.show(supportFragmentManager, "entry_details")
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecentEntries()
    }
}
