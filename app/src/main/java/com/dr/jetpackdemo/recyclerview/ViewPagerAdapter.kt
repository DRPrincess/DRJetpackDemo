package com.dr.jetpackdemo.recyclerview

import android.content.ContextWrapper
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dr.jetpackdemo.R

/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class ViewPagerAdapter() :
    RecyclerView.Adapter<VideoHolder>() {

    private val TAG = "ViewPagerAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_viewpager_item_view, parent, false)

        val holder = VideoHolder(itemView)

        Log.d(TAG, "onCreateViewHolder---$holder")
        return holder
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onFailedToRecycleView(holder: VideoHolder): Boolean {

        Log.e(TAG, "onFailedToRecycleView---$holder")
        return super.onFailedToRecycleView(holder)
    }
    override fun onViewAttachedToWindow(holder: VideoHolder) {
        super.onViewAttachedToWindow(holder)
        Log.i(TAG, "onViewAttachedToWindow---$holder")
    }

    override fun onViewDetachedFromWindow(holder: VideoHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.i(TAG, "onViewDetachedFromWindow---$holder")

    }

    override fun onViewRecycled(holder: VideoHolder) {
        super.onViewRecycled(holder)
        Log.w(TAG, "onViewRecycled---$holder")

    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv).let {
            it.text = "position:$position"
            if(position == 0){
                it.setBackgroundColor(Color.parseColor("#D81B60"))
            }else{
                it.setBackgroundColor(Color.parseColor("#A9A9A9"))
            }
        }
        Log.w(TAG, "onBindViewHolder---$holder")
    }


}

class VideoHolder
    (itemView: View) : RecyclerView.ViewHolder(itemView) {


}