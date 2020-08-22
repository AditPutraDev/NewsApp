package id.mentoring.news.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.mentoring.news.R
import kotlinx.android.synthetic.main.content_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val image = intent.getStringExtra("image")
        val publish = intent.getStringExtra("publish")

        tv_title_detail.text = title
        tv_description_detail.text = description
        tv_publish.text = publish
        Glide.with(this).load(image).into(image_detail)
    }
}