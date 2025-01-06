package com.yesandroid.newsapp

import android.app.Application
import android.content.Context

class GlobalVariable : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext as GlobalVariable
//        val shcDatabase = ShcDatabase.getInstance()
    }

    companion object{
        var context: GlobalVariable? = null

        fun getAppContext(): Context? {
            return context
        }
    }
}