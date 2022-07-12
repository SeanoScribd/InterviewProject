package com.sean.oxford.scribdproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.factory.AppFragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

}