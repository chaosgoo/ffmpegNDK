package com.chaosgoo.ffmpegproject;

import android.util.Log;

/**
 * Created by Goo on 2018/2/1.
 */

public class ffmpegJni {
    int decodeTime = 0;
    int totalDecodeTime = 0;
    public static void onProgress(int second) {
        Log.d("AAA", "已执行时长:" + second);
    }

    public void durationTime(int total){
        totalDecodeTime = total;
        Log.d("ffmpeg","asd");
    }

    public static void TestFunc(int s){
        Log.d("FFmpeg","Called from JNI");
    }
    // NDK 函数
    public native int run(String[] commands);
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