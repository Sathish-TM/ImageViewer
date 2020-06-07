package com.example.imageviewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageviewer.ImageAdapter.MasonryView

class ImageAdapter(private val context: Context,private val imageList:ArrayList<String>) : RecyclerView.Adapter<MasonryView>() {

    var imgList = intArrayOf(
        R.drawable.two, R.drawable.one, R.drawable.three, R.drawable.four,
        R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
        R.drawable.nine, R.drawable.ten
    )
    var nameList = arrayOf(
        "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasonryView {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return MasonryView(layoutView)
    }

    override fun onBindViewHolder(holder: MasonryView, position: Int) {
       // Glide.with(context)
          //  .load(imgList[position])
          //  .into(holder.imageView)
        holder.imageView.setImageResource(imgList[position])
        holder.textView.text = nameList[position]
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    inner class MasonryView(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView
        init {
            imageView = itemView.findViewById<View>(R.id.img) as ImageView
            textView = itemView.findViewById<View>(R.id.img_name) as TextView
        }
    }

}