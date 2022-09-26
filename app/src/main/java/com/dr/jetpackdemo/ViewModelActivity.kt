package com.dr.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider



class ViewModelActivity : AppCompatActivity() {

    private  val TAG = "ViewModelActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        //构建ViewModel实例
        val userModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(UserModel::class.java)

        //让TextView观察ViewModel中数据的变化,并实时展示
        userModel.mUserLiveData.observe(this, Observer<User> {
        })
    }
}
