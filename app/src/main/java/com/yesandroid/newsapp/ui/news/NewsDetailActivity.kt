package com.yesandroid.newsapp.ui.news

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.yesandroid.newsapp.R
import com.yesandroid.newsapp.db.NewsDatabase
import com.yesandroid.newsapp.rx.AppSchedulerProvider

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvAuthor = findViewById<TextView>(R.id.tvAuthor)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvContent=findViewById<TextView>(R.id.tvContent)


        val id = intent.getIntExtra("id", -1)

        if(id>=0)
        {
            AppSchedulerProvider.getInstance().io().execute {
                val newsEntity=NewsDatabase.getInstance().newsDao().getNewsById(id)

                AppSchedulerProvider.getInstance().ui().execute{
                    if(newsEntity!=null)
                    {
                        tvTitle.text = newsEntity.title ?:"No Title"
                        tvAuthor.text = newsEntity.author?: "Unknown"
                        tvDescription.text = newsEntity.description?:"No Description"
                        tvContent.text=newsEntity.content?:"No Content"

                        if (newsEntity.urlToImage != null) {
                            Picasso.get().load(newsEntity.urlToImage).into(imageView)
                        } else {
                            imageView.setImageResource(R.drawable.ic_launcher_background)
                        }
                    }

                }
            }
        }


    }
}