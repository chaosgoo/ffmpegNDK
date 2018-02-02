package com.chaosgoo.ffmpegproject;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Goo on 2018/2/1.
 */

public class ffmpegJni {
    private  float totalDecodeTime = 349.0f;
    private  ProgressBar progressBar;

    private String fileInPath;
    private String fileOutPath;

    MediaPlayer mediaPlayer = new MediaPlayer();

    public ffmpegJni(String inPath, String outPath, ProgressBar progressBar){
        fileInPath = inPath;
        fileOutPath = outPath;
        this.progressBar = progressBar;
    }
    // 初始化相关设定
    public void init(){
        try{
            mediaPlayer.setDataSource(fileInPath);
            mediaPlayer.prepare();
            totalDecodeTime = mediaPlayer.getDuration()/1000;
            Log.d("ffmpeg", "init: "+totalDecodeTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //执行解码操作
    public void decode(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] cmd = {"ffmpeg", "-i",fileInPath, "-f", "s16be" ,"-ar" ,"44100" ,"-acodec","pcm_s16le", fileOutPath};
                main(cmd);
            }
        }).start();
    }

    // 更新进度条
    public void onProgress(int second) {
        Log.d("ffmpeg", "onProgress: "+ ((second/this.totalDecodeTime) * 100));
        progressBar.setProgress((int)((second/this.totalDecodeTime) * 100));
    }


    // NDK 函数
    public native int main(String[] commands);
    // 导入so文件
    static {
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avfilter");
        System.loadLibrary("avdevice");
        System.loadLibrary("ffmpegjni");
    }
}