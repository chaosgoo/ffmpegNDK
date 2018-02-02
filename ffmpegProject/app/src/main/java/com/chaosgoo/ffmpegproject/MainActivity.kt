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

        test_btn.setOnClickListener( View.OnClickListener {
            ffmpegJni.TestFunc(1)
            var cmd = input_text.text.toString()
            var strs = arrayOf("ffmpeg","-codecs","-help")
            var mp3topcm = arrayOf("ffmpeg", "-i","/storage/emulated/0/aaa.mp3", "-f", "s16be" ,"-ar" ,"44100" ,"-acodec","pcm_s16le", "/storage/emulated/0/zzz.pcm")
            var mp3info = arrayOf("ffmpeg", "-i","/storage/emulated/0/aaa.mp3")
            //var mp3towav = arrayOf("ffmpeg", "-i","/storage/emulated/0/aaa.mp3", "-f", "s16be" ,"-ar" ,"44100" ,"-acodec","pcm_s16le", "/storage/emulated/0/zzz.pcm")
            try {
                f.run(mp3info)
            }catch (e:Exception){
                e.printStackTrace()
            }
        })
    }
}
