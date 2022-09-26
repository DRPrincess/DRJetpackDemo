package com.dr.jetpackdemo.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dr.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_recycler_view.*


/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = DemoAdapter(recyclerView)



        recyclerViewOther.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
//        val horizontalManager =
//            StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)


        recyclerViewOther.adapter = MyAdapter(recyclerViewOther)


    }
}




