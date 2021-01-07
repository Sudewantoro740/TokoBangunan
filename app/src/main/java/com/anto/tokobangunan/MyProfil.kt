package com.anto.tokobangunan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyProfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofil)
        actionBar?.hide()
    }
}