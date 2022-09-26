package com.dr.jetpackdemo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.dr.jetpackdemo.lifecycle.ActivityLifeCycleObserver
import com.dr.jetpackdemo.lifecycle.AppLifeCycleObserver

/**
 * 功能：
 * 作者： duanrui
 * 时间： 3/28/22
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifeCycleObserver())
        ProcessLifecycleOwner.get().lifecycle.addObserver(ActivityLifeCycleObserver())
    }
}