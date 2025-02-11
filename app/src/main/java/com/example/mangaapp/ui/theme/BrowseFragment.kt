package com.example.mangaapp.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.R
import com.example.mangaapp.data.Manga
import com.example.mangaapp.databinding.FragmentBrowseBinding
import com.example.mangaapp.ui.MangaAdapter

class BrowseFragment : Fragment() {
    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample Manga List
        val sampleMangaList = listOf(
            Manga(
                title = "Naruto",
                author = "Masashi Kishimoto",
                genre = "Shonen",
                chaptersRead = 100,
                totalChapters = 700,
                posterUrl = "https://upload.wikimedia.org/wikipedia/en/9/94/NarutoCoverTankobon1.jpg",
                description = "Naruto Uzumaki, a young ninja, seeks recognition and dreams of becoming the Hokage, the village leader.",
                rating = 8.9
            ),
            Manga(
                title = "One Piece",
                author = "Eiichiro Oda",
                genre = "Adventure",
                chaptersRead = 1050,
                totalChapters = 1100,
                posterUrl = "https://upload.wikimedia.org/wikipedia/en/6/6f/One_Piece%2C_Volume_61_Cover_%28Japanese%29.jpg",
                description = "Monkey D. Luffy and his crew explore the Grand Line in search of the legendary treasure, One Piece.",
                rating = 9.2
            ),
            Manga(
                    "Bleach", "Tite Kubo", "Supernatural", 300, 366,
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/7/72/Bleachcover01.jpg",
            description = "Ichigo Kurosaki gains the powers of a Soul Reaper and protects humanity from evil spirits called Hollows.",
            rating = 8.5
        ),
        Manga(
            "My Hero Academia", "Kohei Horikoshi", "Superhero", 400, 500,
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/6/60/My_Hero_Academia%2C_Volume_1.jpg",
            description = "In a world where superpowers, or Quirks, are common, Izuku Midoriya strives to become the greatest hero.",
            rating = 8.8
        )
        )

        // Setup RecyclerView with Adapter
        binding.recyclerViewManga.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewManga.adapter = MangaAdapter(sampleMangaList) { selectedManga ->
            val bundle = Bundle().apply {
                putSerializable("manga", selectedManga)  // ✅ Sending the full Manga object
            }
            findNavController().navigate(R.id.detailsFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
