package com.dr.dr_jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != savedInstanceState) {
            val name = savedInstanceState.getString("name")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("name", "小明")
        super.onSaveInstanceState(outState)
    }
}
