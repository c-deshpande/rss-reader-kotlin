package com.cdeshpande.rss_reader_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cdeshpande.rss_reader_kotlin.*
import com.cdeshpande.rss_reader_kotlin.adapters.RSSAdapter
import com.cdeshpande.rss_reader_kotlin.data.RSSItem
import com.cdeshpande.rss_reader_kotlin.networking.API
import com.cdeshpande.rss_reader_kotlin.networking.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.feedToolbar)
        toolbar.inflateMenu(R.menu.main_menu)

        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.btn_refresh -> {
                    getFeedData()
                    true
                }
                else -> false
            }
        }

        getFeedData()
    }

    private fun getFeedData() {
        val api: API = RetrofitInstance.getRetrofitInstance.create(
            API::class.java
        )
        val call: Call<RSSItem> = api.getNews()

        call.enqueue(object : Callback<RSSItem> {
            override fun onResponse(call: Call<RSSItem>, response: Response<RSSItem>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Successful response", Toast.LENGTH_SHORT)
                        .show()
                    feedInit(response.body()!!.items)
                }
            }

            override fun onFailure(call: Call<RSSItem>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "No response, Check your internet connection!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun feedInit(rssItemsList: List<RSSItem.Item>) {

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RSSAdapter(
                this@MainActivity,
                rssItemsList
            )
        }
    }
}