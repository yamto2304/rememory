package com.sunasterisk.rememory.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunasterisk.rememory.ui.main.MainActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
