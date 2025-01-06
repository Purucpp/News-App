package com.yesandroid.newsapp.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yesandroid.newsapp.GlobalVariable
import com.yesandroid.newsapp.db.dao.NewsDao
import com.yesandroid.newsapp.db.entity.NewsEntity


@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao():NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(): NewsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    GlobalVariable.getAppContext()!!,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}