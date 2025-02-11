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
import com.example.mangaapp.databinding.FragmentLibraryBinding
import com.example.mangaapp.ui.MangaAdapter

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    // Sample Manga List for Library (Local Drawables & Online URLs)
    private val mangaList = listOf(
        Manga(
            title = "Naruto",
            author = "Masashi Kishimoto",
            genre = "Shonen",
            chaptersRead = 100,
            totalChapters = 700,
            posterUrl = "naruto",  // ✅ Local drawable (res/drawable/naruto.png)
            description = "Naruto Uzumaki, a young ninja, seeks recognition and dreams of becoming the Hokage, the village leader.",
            rating = 8.9
        ),
        Manga(
            title = "One Piece",
            author = "Eiichiro Oda",
            genre = "Adventure",
            chaptersRead = 1050,
            totalChapters = 1100,
            posterUrl = "one_piece",  // ✅ Local drawable (res/drawable/one_piece.png)
            description = "Monkey D. Luffy and his crew explore the Grand Line in search of the legendary treasure, One Piece.",
            rating = 9.2
        ),
        Manga(
            title = "Attack on Titan",
            author = "Hajime Isayama",
            genre = "Action",
            chaptersRead = 140,
            totalChapters = 140,
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/9/9f/Shingeki_no_Kyojin_manga_volume_1.jpg", // ✅ Online URL
            description = "In a world where humanity is on the brink of extinction due to giant humanoid Titans, Eren Yeager fights for survival.",
            rating = 9.1
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = MangaAdapter(mangaList) { selectedManga ->
            val bundle = Bundle().apply {
                putSerializable("manga", selectedManga) // ✅ Pass manga object to LibraryMangaFragment
            }
            findNavController().navigate(R.id.action_libraryFragment_to_libraryMangaFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
