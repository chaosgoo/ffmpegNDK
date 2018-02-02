package com.chaosgoo.ffmpegproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.chaosgoo.ffmpegproject.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var f: ffmpegJni = ffmpegJni("/storage/emulated/0/aaa.mp3","/storage/emulated/0/zzz.pcm",progressBar)
        f!!.init()
        test_btn.setOnClickListener( View.OnClickListener {
              f!!.decode()
        })
    }
}
