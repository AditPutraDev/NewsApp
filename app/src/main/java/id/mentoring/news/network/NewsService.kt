package id.mentoring.news.network

import id.mentoring.news.models.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("v2/everything?q=bitcoin&from=2020-07-23&sortBy=publishedAt&apiKey=d32dd7d06e3b40b8ab47fb94dfbe8ac4")
    fun getNews(): Call<ResponseServer>
}