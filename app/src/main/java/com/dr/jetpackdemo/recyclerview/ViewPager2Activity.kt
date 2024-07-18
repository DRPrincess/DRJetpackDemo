package com.dr.jetpackdemo.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dr.jetpackdemo.R
import com.dr.jetpackdemo.databinding.ActivityViewPager2Binding

class ViewPager2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPager2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter()
        val viewPager =  binding.viewPager
        val recyclerView =  binding.viewPager.getChildAt(0) as RecyclerView
        val targetPosition =  1
        binding.tvRest.setOnClickListener {
            viewPager.setCurrentItem(0,false)
        }
        binding.tvCurrentTo.setOnClickListener {
            viewPager.setCurrentItem(targetPosition,false)
        }
        binding.tvSmoothCurrent.setOnClickListener {
            viewPager.setCurrentItem(targetPosition,true)
        }
        binding.tvScrollTo.setOnClickListener {
            recyclerView.scrollToPosition(targetPosition)
        }
        binding.tvSmoothScrollTo.setOnClickListener {
            recyclerView.smoothScrollToPosition(targetPosition)
        }
    }
}