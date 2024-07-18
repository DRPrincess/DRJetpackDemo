package com.dr.jetpackdemo.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.jetpackdemo.databinding.ActivityRecyclerViewBinding



/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class RecyclerViewActivity : AppCompatActivity() {

    private  val TAG = "RecyclerViewActivity"
    private lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.adapter = DemoAdapter( binding.recyclerView)

        val test = 0.1+0.2
        Log.i(TAG,"test----"+test)
        if(test == 0.3){
            Log.i(TAG,"test---true")
        }else{
            Log.i(TAG,"test---false")
        }
//
//        binding.recyclerViewOther.layoutManager = LinearLayoutManager(
//            this,
//            LinearLayoutManager.HORIZONTAL,
//            false
//        )
////        val horizontalManager =
////            StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL)
//
//
//        binding.recyclerViewOther.adapter = MyAdapter( binding.recyclerViewOther)


    }
}




