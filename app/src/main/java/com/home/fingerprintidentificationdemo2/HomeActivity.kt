package com.home.fingerprintidentificationdemo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_lock.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initializeView()
    }

    private fun initializeView() {
        Glide.with(this).load(R.drawable.icon_home)
            .into(image_view_background)
    }
}
