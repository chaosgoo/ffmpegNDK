package com.chaosgoo.ffmpegproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.chaosgoo.ffmpegproject.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var f: ffmpegJni = ffmpegJni()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input_text.setText("ffmpeg -i /storage/emulated/0/aaa.mp3 /storage/emulated/0/zzz.wav")
        input_text.setText("ffmpeg -i /storage/emulated/0/aaa.mp3 -acodec copy -f mp3 /storage/emulated/0/zzz.mp3")

        test_btn.setOnClickListener( View.OnClickListener {

            var cmd = input_text.text.toString()
            var strs = arrayOf("ffmpeg","-i","/storage/emulated/0/aaa.mp3","/storage/emulated/0/zzz.wav")
            f.run2(strs)
            //Log.d("FFMPEG","$cmd")
            output.setText(f.avcodecinfo().toString())
        })
    }
}
