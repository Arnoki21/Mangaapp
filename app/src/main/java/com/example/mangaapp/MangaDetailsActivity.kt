package com.example.mangaapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2

class MangaDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view
        setContentView(R.layout.activity_manga_details)

        // Enable edge-to-edge layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Retrieve data from intent
        val mangaTitle = intent.getStringExtra("title") ?: "Unknown Title"
        val mangaPages = intent.getStringArrayListExtra("pages") ?: arrayListOf()

        // Set the title in the TextView
        findViewById<TextView>(R.id.manga_title).text = mangaTitle

        // Set up the ViewPager2 to display manga pages
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = MangaPageAdapter(mangaPages)
    }
}
