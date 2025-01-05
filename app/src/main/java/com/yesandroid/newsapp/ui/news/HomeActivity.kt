package com.yesandroid.newsapp.ui.news

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yesandroid.newsapp.R
import com.yesandroid.newsapp.api.Get_Interface
import com.yesandroid.newsapp.api.Get_Retrofit_Client
import com.yesandroid.newsapp.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
          /*  try {
                val posts = RetrofitInstance.api.getNews()
                Log.d("API_RESPONSE", "Posts: $posts")
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error: ${e.message}")
            }*/


            val client= Get_Retrofit_Client().getClient();
            val service = client?.create(Get_Interface::class.java)
            val call = service?.getNews()

            call?.enqueue(object : Callback<ResponseBody?>
            {

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.d("Failed------", t.message.toString());

                }

                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Log.d("res",response.body()!!.string())
//                    TODO("Not yet implemented")
                }
            })
        }
    }
}