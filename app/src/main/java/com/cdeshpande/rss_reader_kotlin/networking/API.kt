package com.cdeshpande.rss_reader_kotlin.networking
import com.cdeshpande.rss_reader_kotlin.data.RSSItem
import retrofit2.http.GET

interface API {

    @GET("/v1/api.json?rss_url=https%3A%2F%2Frss.nytimes.com%2Fservices%2Fxml%2Frss%2Fnyt%2FTechnology.xml")
    fun getNews(): retrofit2.Call<RSSItem>
}