package com.yesandroid.newsapp.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yesandroid.newsapp.R
import com.yesandroid.newsapp.db.entity.NewsEntity

class NewsAdapter(private val articles: List<NewsEntity>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = articles.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(article: NewsEntity) {
            tvTitle.text = article.title ?: "No Title"
            tvAuthor.text = "By: ${article.author ?: "Unknown"}"
            tvDescription.text = article.description ?: "No Description Available"
            if (article.urlToImage != null) {
                Picasso.get().load(article.urlToImage).into(imageView)
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }
}
