package com.example.mangaapp.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.mangaapp.R
import com.example.mangaapp.data.Manga
import com.example.mangaapp.databinding.FragmentLibraryMangaBinding

class LibraryMangaFragment : Fragment() {
    private var _binding: FragmentLibraryMangaBinding? = null
    private val binding get() = _binding!!

    private var currentPage = 1
    private val totalPages = 20 // Example total pages

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve Manga object
        val manga = arguments?.getSerializable("manga") as? Manga

        // Display Manga Data
        manga?.let {
            binding.mangaTitle.text = it.title

            // Load Manga Cover
            binding.mangaCover.load(it.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.firstpage)
                error(R.drawable.firstpage)
            }
        }

        // Page Navigation
        updatePageIndicator()

        binding.nextPageButton.setOnClickListener {
            if (currentPage < totalPages) {
                currentPage++
                updatePageIndicator()
                loadNextPage()
            }
        }

        binding.prevPageButton.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                updatePageIndicator()
                loadPreviousPage()
            }
        }

        // Save to Favorites
        binding.favoriteButton.setOnClickListener {
            saveToFavorites(manga)
        }
    }

    private fun updatePageIndicator() {
        binding.pageIndicator.text = "Page $currentPage of $totalPages"
    }

    private fun loadNextPage() {
        // Example: Load the next page image from drawable resources
        val nextPageResource = when (currentPage) {
            2 -> R.drawable.page2
            3 -> R.drawable.page3
            else -> R.drawable.firstpage
        }
        binding.mangaCover.setImageResource(nextPageResource)
    }

    private fun loadPreviousPage() {
        val previousPageResource = when (currentPage) {
            1 -> R.drawable.firstpage
            2 -> R.drawable.page2
            else -> R.drawable.page3
        }
        binding.mangaCover.setImageResource(previousPageResource)
    }

    private fun saveToFavorites(manga: Manga?) {
        // Save manga to local database or shared preferences
        // Example: Toast message for now
        manga?.let {
            // Implement actual save logic here
            println("Saved ${it.title} to favorites!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
