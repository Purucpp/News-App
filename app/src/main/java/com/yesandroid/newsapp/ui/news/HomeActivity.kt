package com.yesandroid.newsapp.ui.news

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yesandroid.newsapp.R
import com.yesandroid.newsapp.api.ApiUtils
import com.yesandroid.newsapp.api.Get_Interface
import com.yesandroid.newsapp.api.Get_Retrofit_Client
import com.yesandroid.newsapp.db.NewsDatabase
import com.yesandroid.newsapp.db.entity.NewsEntity
import com.yesandroid.newsapp.rx.AppSchedulerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)


        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        recyclerView.addItemDecoration(ItemSpacingDecoration(spacing))


        AppSchedulerProvider.getInstance().io().execute{
            val count=NewsDatabase.getInstance().newsDao().getNewsCount()
            if(count==0)
            {
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

                            if(response.body()!=null)
                            {
                                val responseString = response.body()!!.string()
                                val jsonObject = JSONObject(responseString)

                                if(ApiUtils.isJsonArrayPresent(jsonObject,"articles"))
                                {
                                    val jsonArray=jsonObject.getJSONArray("articles")
                                    for(i in 0 until jsonArray.length())
                                    {
                                        val newsEntity=NewsEntity()
                                        newsEntity.id=i

                                        var newsItem=jsonArray.getJSONObject(i)

                                        if(ApiUtils.isStringPresent(newsItem,"author"))
                                        {
                                            newsEntity.author=newsItem.getString("author")
                                        }

                                        if(ApiUtils.isStringPresent(newsItem,"title"))
                                        {
                                            newsEntity.title=newsItem.getString("title")
                                        }

                                        if(ApiUtils.isStringPresent(newsItem,"description"))
                                        {
                                            newsEntity.description=newsItem.getString("description")
                                        }

                                        if(ApiUtils.isStringPresent(newsItem,"urlToImage"))
                                        {
                                            newsEntity.urlToImage=newsItem.getString("urlToImage")
                                        }

                                        if(ApiUtils.isStringPresent(newsItem,"content"))
                                        {
                                            newsEntity.content=newsItem.getString("content")
                                        }

                                        if(ApiUtils.isStringPresent(newsItem,"publishedAt"))
                                        {
                                            newsEntity.publishedAt=newsItem.getString("publishedAt")
                                        }


                                        AppSchedulerProvider.getInstance().io().execute {
                                            NewsDatabase.getInstance().newsDao().add(newsEntity)
                                        }

                                    }


                                    val handler = Handler(Looper.getMainLooper())

// Post a delayed task
                                    handler.postDelayed({

                                        println("This runs after a delay!")


                                        updateView()
                                    }, 6000)


                                }
                            }
                            Log.d("res",response.body()!!.string())
//                    TODO("Not yet implemented")
                        }
                    })
            }else{
                updateView()
            }
        }


    }

    fun updateView(){
        AppSchedulerProvider.getInstance().io().execute {
            val newsEntityList=NewsDatabase.getInstance().newsDao().getNews()

            if(newsEntityList!=null)
            {
                if(newsEntityList.isNotEmpty())
                {
                    try {
                        recyclerView.adapter=NewsAdapter(this,newsEntityList)
                    }catch (e:Exception)
                    {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}