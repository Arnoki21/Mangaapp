package com.example.mangaapp.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.mangaapp.R
import com.example.mangaapp.data.Manga
import com.example.mangaapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve manga data from bundle
        val manga = arguments?.getSerializable("manga") as? Manga


        // Set data
        manga?.let {
            binding.detailsTitle.text = it.title
            binding.detailsAuthor.text = "By ${it.author}"
            binding.detailsGenre.text = "Genre: ${it.genre}"
            binding.detailsChapters.text = "Chapters: ${it.totalChapters}"
            binding.detailsRating.text = "⭐ ${it.rating}"
            binding.detailsDescription.text = it.description

            // Load cover image with Coil
            binding.detailsCover.load(it.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
        }

        // Handle Read Now button click
        binding.readButton.setOnClickListener {
            // Implement navigation to the reader section
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
