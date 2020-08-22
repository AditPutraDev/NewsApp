package id.mentoring.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.mentoring.news.R
import id.mentoring.news.models.News
import id.mentoring.news.ui.NewsDetailActivity
import kotlinx.android.synthetic.main.item_news.view.*


class NewsAdapter(var articles: ArrayList<News>?) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.iv_image
        val title = itemView.tv_title
        val description = itemView.tv_description
        val publish = itemView.tv_publish

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val holder = NewsHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.title.text = articles?.get(position)?.title
        holder.description.text = articles?.get(position)?.description
        holder.publish.text = articles?.get(position)?.publishedAt?.subSequence(0,10)
        Glide.with(holder.itemView.context).load(articles?.get(position)?.urlToImage).into(holder.img)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,NewsDetailActivity::class.java)
            intent.putExtra("title",articles?.get(position)?.title)
            intent.putExtra("description",articles?.get(position)?.description)
            intent.putExtra("image",articles?.get(position)?.urlToImage)
            intent.putExtra("publish", articles?.get(position)?.publishedAt?.subSequence(0,10))
            holder.itemView.context.startActivity(intent)
        }
    }
}