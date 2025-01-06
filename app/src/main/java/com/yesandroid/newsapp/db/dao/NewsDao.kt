package com.yesandroid.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yesandroid.newsapp.db.entity.NewsEntity


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(newsEntity: NewsEntity)
    @Query("Select * from t_news ")
    fun getNews(): List<NewsEntity>?

    @Query("Select count(*) from t_news ")
    fun getNewsCount(): Int

    @Query("SELECT * FROM t_news WHERE c_id = :newsId")
    fun getNewsById(newsId: Int): NewsEntity?

    @Query("DELETE FROM t_news")
    fun nukeTable()
}