package com.dr.jetpackdemo.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.internal.SafeIterableMap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dr.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : AppCompatActivity() {


    private lateinit var myLocationListener: MyLocationListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(ActivityLifeCycleObserver())

        val fragment = LifecycleFragment()


        btn_add.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.fragment, fragment).commit()
        }

        btn_remove.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }

        testSafeIterableMap()
ø


    }


    fun testSafeIterableMap() {
        val safeIterableMap: SafeIterableMap<String, String> = SafeIterableMap()

        //添加数据
        for (i in 0..9) {
            safeIterableMap.putIfAbsent(i.toString(), "value_$i")
        }
        Log.i("safeIterableMap","after add :$safeIterableMap")


        //添加数据
        for (i in 0..2) {
            when(i){
                0-> {
                    Thread{
                        //遍历
                        safeIterableMap.descendingIterator().forEach {
                            Log.i("safeIterableMap","descendingIterator :$it")
                            if(it.key == "0"){
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap","descendingIterator remove:$it")
                            }
                            if(it.key == ""){
                                safeIterableMap.putIfAbsent("test_add", "value_test_add")
                                Log.e("safeIterableMap","descendingIterator putIfAbsent")
                            }

                        }
                        Log.i("safeIterableMap","---------------------------------"+Thread.currentThread())

                    }.start()
                }
                1-> {
                    Thread {
                        //遍历
                        safeIterableMap.iterator().forEach {
                            Log.i("safeIterableMap", "ascendingIterator :$it")
                            if (it.key == "1") {
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap", "ascendingIterator remove:$it")
                            }
                        }
                        Log.i(
                            "safeIterableMap",
                            "---------------------------------" + Thread.currentThread()
                        )

                    }.start()
                }
                2-> {
                    Thread{
                        safeIterableMap.iteratorWithAdditions().forEach {
                            Log.i("safeIterableMap","iteratorWithAdditions :$it")
                            if(it.key == "2"){
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap","iteratorWithAdditions remove:$it")
                            }
                        }
                        Log.i("safeIterableMap","---------------------------------"+Thread.currentThread())

                    }.start()
                }
            }
        }





//        safeIterableMap.iterator().forEach {
//            Log.i("safeIterableMap","ascendingIterator :$it")
//        }
//        Log.i("safeIterableMap","---------------------------------")
//
//        safeIterableMap.iteratorWithAdditions().forEach {
//            Log.i("safeIterableMap","iteratorWithAdditions :$it")
//        }


        //移除
        safeIterableMap.remove("8")
        Log.i("safeIterableMap","afterremove : safeMap = $safeIterableMap")
    }


    fun testHashMapMap() {
        val safeIterableMap: SafeIterableMap<String, String> = SafeIterableMap()

        //添加数据
        for (i in 0..9) {
            safeIterableMap.putIfAbsent(i.toString(), "value_$i")
        }
        Log.i("safeIterableMap","after add :$safeIterableMap")


        //添加数据
        for (i in 0..2) {
            when(i){
                0-> {
                    Thread{
                        //遍历
                        safeIterableMap.descendingIterator().forEach {
                            Log.i("safeIterableMap","descendingIterator :$it")
                            if(it.key == "0"){
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap","descendingIterator remove:$it")
                            }

                        }
                        Log.i("safeIterableMap","---------------------------------"+Thread.currentThread())

                    }.start()
                }
                1-> {
                    Thread {
                        //遍历
                        safeIterableMap.iterator().forEach {
                            Log.i("safeIterableMap", "ascendingIterator :$it")
                            if (it.key == "1") {
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap", "ascendingIterator remove:$it")
                            }
                        }
                        Log.i(
                            "safeIterableMap",
                            "---------------------------------" + Thread.currentThread()
                        )

                    }.start()
                }
                2-> {
                    Thread{
                        safeIterableMap.iteratorWithAdditions().forEach {
                            Log.i("safeIterableMap","iteratorWithAdditions :$it")
                            if(it.key == "2"){
                                safeIterableMap.remove(it.key)
                                Log.e("safeIterableMap","iteratorWithAdditions remove:$it")
                            }
                        }
                        Log.i("safeIterableMap","---------------------------------"+Thread.currentThread())

                    }.start()
                }
            }
        }





//        safeIterableMap.iterator().forEach {
//            Log.i("safeIterableMap","ascendingIterator :$it")
//        }
//        Log.i("safeIterableMap","---------------------------------")
//
//        safeIterableMap.iteratorWithAdditions().forEach {
//            Log.i("safeIterableMap","iteratorWithAdditions :$it")
//        }


        //移除
        safeIterableMap.remove("8")
        Log.i("safeIterableMap","afterremove : safeMap = $safeIterableMap")
    }

    internal class MyLocationListener : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun connectListener() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun disconnectListener() {
        }
    }
}
