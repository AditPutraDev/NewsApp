package id.mentoring.news

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.mentoring.news.adapter.NewsAdapter
import id.mentoring.news.models.News
import id.mentoring.news.models.ResponseServer
import id.mentoring.news.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isConnected()){

        ConfigNetwork.getRetrofit().getNews().enqueue(object :Callback<ResponseServer>{
            override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                progress_circular.visibility = View.GONE
                t.message?.let { Log.d("error server", it) }
            }

            override fun onResponse(
                call: Call<ResponseServer>,
                response: Response<ResponseServer>
            ) {
                Log.d("response server", response.message())
                if(response.isSuccessful){
                    progress_circular.visibility = View.GONE
                    val status = response.body()?.status
                    if (status == "ok"){
                        val articles = response.body()?.articles
                        showData(articles)
                    }
                }
            }
        })} else {
            progress_circular.visibility = View.GONE
            Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showData(articles: ArrayList<News>?) {
      rv_news.adapter = NewsAdapter(articles)
       rv_news.layoutManager = LinearLayoutManager(this)
    }

    fun isConnected() : Boolean{
        val connect: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}