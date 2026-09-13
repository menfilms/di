package com.emotiondiary.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emotiondiary.app.data.EmotionEntry
import com.emotiondiary.app.databinding.DialogSupportBinding
import com.emotiondiary.app.utils.SupportMessages
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

class SupportDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogSupportBinding? = null
    private val binding get() = _binding!!

    private var entry: EmotionEntry? = null

    companion object {
        private const val ARG_ENTRY_ID = "entry_id"
        private const val ARG_MOOD = "mood"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(entry: EmotionEntry): SupportDialogFragment {
            return SupportDialogFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ENTRY_ID, entry.id)
                    putInt(ARG_MOOD, entry.mood)
                    putString(ARG_DESCRIPTION, entry.description)
                }
            }
        }

        fun newSupportInstance(mood: Int): SupportDialogFragment {
            return SupportDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOOD, mood)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogSupportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mood = arguments?.getInt(ARG_MOOD, 5) ?: 5
        val dateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale("ru"))

        binding.tvMoodEmoji.text = when {
            mood >= 7 -> "😊"
            mood >= 4 -> "🙂"
            else -> "🤗"
        }

        if (mood <= 3) {
            // Показываем поддерживающее сообщение
            val supportMessage = SupportMessages.getRandomMessage()
            binding.tvSupportTitle.text = "Мы рядом с тобой 💙"
            binding.tvSupportMessage.text = supportMessage
            binding.tvMoodEmoji.text = "🤗"

            binding.btnAffirmation.setOnClickListener {
                binding.tvSupportMessage.text = SupportMessages.getRandomAffirmation()
            }

            binding.btnTip.setOnClickListener {
                binding.tvSupportMessage.text = SupportMessages.getRandomTip()
            }
        } else {
            binding.tvSupportTitle.text = "Отличное настроение!"
            binding.tvSupportMessage.text = "Прекрасно, что ты чувствуешь себя хорошо! Продолжай вести дневник, чтобы отслеживать свои эмоции."
            binding.btnAffirmation.visibility = View.GONE
            binding.btnTip.visibility = View.GONE
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
