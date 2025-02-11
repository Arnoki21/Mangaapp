package com.example.mangaapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.mangaapp.R
import com.example.mangaapp.data.Manga

class MangaAdapter(
    private val mangaList: List<Manga>,
    private val onItemClick: (Manga) -> Unit
) : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    inner class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.manga_title)
        private val author: TextView = view.findViewById(R.id.manga_author)
        private val genre: TextView = view.findViewById(R.id.manga_genre)
        private val poster: ImageView = view.findViewById(R.id.manga_cover)

        fun bind(manga: Manga) {
            title.text = manga.title
            author.text = "By ${manga.author}"
            genre.text = manga.genre

            // ✅ Handle empty/missing poster URL safely
            if (manga.posterUrl.isNullOrEmpty()) {
                poster.isVisible = false
            } else {
                poster.isVisible = true
                poster.load(manga.posterUrl) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(16f))
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }
            }

            // ✅ Add click animation for better UX
            itemView.setOnClickListener {
                it.animate().scaleX(0.95f).scaleY(0.95f).setDuration(150).withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(150)
                    onItemClick(manga) // Navigate on click
                }
            }

            // ✅ Set accessibility for screen readers
            itemView.contentDescription = "Manga: ${manga.title}, by ${manga.author}, Genre: ${manga.genre}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manga, parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(mangaList[position])
    }

    override fun getItemCount(): Int = mangaList.size
}
