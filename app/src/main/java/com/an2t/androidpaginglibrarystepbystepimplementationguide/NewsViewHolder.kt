package com.an2t.androidpaginglibrarystepbystepimplementationguide


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: News?) {
        if (news != null) {

            news.title?.let {
                itemView.txt_news_name.text = it
            }
            news.image?.let {
                Picasso.get().load(it).into(itemView.img_news_banner)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}