package com.sunasterisk.rememory.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import androidx.fragment.app.Fragment
import com.sunasterisk.rememory.R
import com.sunasterisk.rememory.ui.addwork.AddWorkFragment
import com.sunasterisk.rememory.ui.history.HistoryFragment
import com.sunasterisk.rememory.ui.main.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageHistory.setOnClickListener(this)
        imageHome.setOnClickListener(this)
        actionButtonAdd.setOnClickListener(this)
        replaceFragment(HomeFragment())
    }

    @SuppressLint("RestrictedApi")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageHome -> replaceFragment(HomeFragment())
            R.id.imageHistory -> replaceFragment(HistoryFragment())
            R.id.actionButtonAdd -> {
                replaceFragment(AddWorkFragment())
                bottomAppBar.visibility = INVISIBLE
                actionButtonAdd.visibility = INVISIBLE
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, fragment)
            .commit()
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
