package com.example.mangaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MangaPageAdapter(private val pages: List<String>) :
    RecyclerView.Adapter<MangaPageAdapter.PageViewHolder>() {

    // ViewHolder to hold the image view
    inner class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.page_image)
    }

    // Inflate the layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_manga_page, parent, false)
        return PageViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.imageView.load(pages[position]) {
            placeholder(R.drawable.placeholder) // Placeholder image while loading
            error(R.drawable.error_image)       // Error image if loading fails
            crossfade(true)                     // Smooth crossfade animation
        }
    }

    // Return the total number of items
    override fun getItemCount(): Int = pages.size
}
