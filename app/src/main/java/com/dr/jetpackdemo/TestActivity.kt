package com.dr.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
