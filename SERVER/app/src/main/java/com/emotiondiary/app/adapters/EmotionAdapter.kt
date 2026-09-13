package com.emotiondiary.app.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotiondiary.app.data.EmotionEntry
import com.emotiondiary.app.databinding.ItemEmotionEntryBinding
import java.text.SimpleDateFormat
import java.util.Locale

class EmotionAdapter(
    private val onItemClick: (EmotionEntry) -> Unit
) : ListAdapter<EmotionEntry, EmotionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmotionEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemEmotionEntryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("ru"))

        fun bind(entry: EmotionEntry) {
            binding.tvMoodEmoji.text = entry.getMoodEmoji()
            binding.tvMoodValue.text = "${entry.mood}/10"
            binding.tvDescription.text = entry.description
            binding.tvDate.text = dateFormat.format(entry.timestamp)

            // Цвет индикатора настроения
            val color = entry.getMoodColor()
            binding.viewMoodIndicator.setBackgroundColor(color)

            // Теги
            if (entry.tags.isNotEmpty()) {
                binding.tvTags.text = entry.tags.split(",").joinToString(" ") { "#$it" }
                binding.tvTags.visibility = ViewGroup.VISIBLE
            } else {
                binding.tvTags.visibility = ViewGroup.GONE
            }

            binding.root.setOnClickListener { onItemClick(entry) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<EmotionEntry>() {
        override fun areItemsTheSame(oldItem: EmotionEntry, newItem: EmotionEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmotionEntry, newItem: EmotionEntry): Boolean {
            return oldItem == newItem
        }
    }
}
