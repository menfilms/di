package com.emotiondiary.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotiondiary.app.adapters.EmotionAdapter
import com.emotiondiary.app.databinding.ActivityHistoryBinding
import com.emotiondiary.app.viewmodels.EmotionViewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: EmotionViewModel
    private lateinit var adapter: EmotionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmotionViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "История записей"
    }

    private fun setupRecyclerView() {
        adapter = EmotionAdapter { entry ->
            val dialog = SupportDialogFragment.newInstance(entry)
            dialog.show(supportFragmentManager, "entry_details")
        }
        binding.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = this@HistoryActivity.adapter
        }
    }

    private fun observeData() {
        viewModel.allEntries.observe(this) { entries ->
            adapter.submitList(entries)
            binding.tvEmptyHistory.visibility = if (entries.isEmpty()) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }

        viewModel.averageMood.observe(this) { average ->
            if (average != null) {
                binding.tvWeekAverage.text = String.format("Среднее за неделю: %.1f/10", average)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
