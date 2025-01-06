package com.yesandroid.newsapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



@Entity(tableName = "t_news")
class NewsEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "c_id")
    var id = 0

    @Expose
    @SerializedName("author")
    @ColumnInfo(name = "author")
    var author: String? = null

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null

    @Expose
    @SerializedName("content")
    @ColumnInfo(name = "content")
    var content: String? = null

    @Expose
    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null

    @Expose
    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null


}