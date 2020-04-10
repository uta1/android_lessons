package com.example.timber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate method called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop method called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause method called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart method called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy method called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume method called")
    }
}
