package com.dr.jetpackdemo.net

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dr.jetpackdemo.databinding.ActivityNetBinding
import com.dr.jetpackdemo.databinding.ActivityRecyclerViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class NetActivity : AppCompatActivity() {

    private  val TAG = "NetActivity"
    private lateinit var binding: ActivityNetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service = retrofit.create(GitHubService::class.java)

        val repos: Call<List<Repo>> = service.listRepos("octocat")
        val repos1: Call<List<String>> = service.listRepos1("octocat")

        repos.enqueue( object: Callback<List<Repo>>{
            override fun onResponse(p0: Call<List<Repo>>, p1: Response<List<Repo>>) {

                Log.d(TAG, "onResponse:$p1")
                binding.textView.text = p1.body()?.size?.toString()?:""
            }

            override fun onFailure(p0: Call<List<Repo>>, p1: Throwable) {
                Log.e(TAG, "onFailure:${p1.message}")
            }

        })
//        repos1.enqueue( object: Callback<List<String>>{
//            override fun onResponse(p0: Call<List<String>>, p1: Response<List<String>>) {
//
//                Log.d(TAG, "onResponse:$p1")
//              //  binding.textView.text = p1.body()?.size?.toString()?:""
//            }
//
//            override fun onFailure(p0: Call<List<String>>, p1: Throwable) {
//                Log.e(TAG, "onFailure:${p1.message}")
//            }
//
//        })

    }
}




