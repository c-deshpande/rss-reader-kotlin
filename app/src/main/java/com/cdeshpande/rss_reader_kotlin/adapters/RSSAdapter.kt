package com.cdeshpande.rss_reader_kotlin.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cdeshpande.rss_reader_kotlin.R
import com.cdeshpande.rss_reader_kotlin.data.RSSItem
import com.cdeshpande.rss_reader_kotlin.activities.WebViewActivity

class RSSAdapter(private val context: Context, private val rssItems: List<RSSItem.Item>) :
    RecyclerView.Adapter<RSSViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RSSViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rssItems.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RSSViewHolder, position: Int) {

        val pubDate : List<String> = rssItems[position].pubDate.split(" ")
        val date = pubDate[0]
        val time = pubDate[1]

        holder.txtTitle.text = rssItems[position].title
        holder.txtDate.text = "$date at $time"
        holder.txtContent.text = rssItems[position].content

        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("Title", rssItems[position].title)
            intent.putExtra("URL", rssItems[position].guid)
            context.startActivity(intent)
        }
    }
}

class RSSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    val txtDate: TextView = itemView.findViewById(R.id.txtDate)
    val txtContent: TextView = itemView.findViewById(R.id.txtContent)
}