package com.chaosgoo.ffmpegproject;

/**
 * Created by Goo on 2018/2/1.
 */

public class ffmpegJni {

    public native int run(String[] commands);

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