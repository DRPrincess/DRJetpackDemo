package com.dr.jetpackdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



/**
 * 功能：
 * 作者： duanrui
 * 时间： 2020-09-14
 */

class UserModel : ViewModel() {

    val mUserLiveData = MutableLiveData<User>()

    init {
        //模拟从网络加载用户信息
        mUserLiveData.postValue(User(1, "name1"))
    }

    //模拟 进行一些数据骚操作
    fun doSomething() {
        val user = mUserLiveData.getValue()
        if (user != null) {
            user!!.age = 15
            user!!.name = "name15"
            mUserLiveData.setValue(user)
        }
    }

}

data class User(var age: Int, var name: String)