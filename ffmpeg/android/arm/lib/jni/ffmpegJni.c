#include "logjni.h"
#include "ffmpegJni.h"
#include "libavcodec/avcodec.h"
#include <stdlib.h>
#include <stdbool.h>

int main(int argc, char **argv);
static JavaVM *jvm = NULL;
static jclass m_clazz = NULL;
static jclass m_jobj = NULL;
static JNIEnv *m_env = NULL;

JNIEXPORT jint JNICALL Java_com_chaosgoo_mixmusic_ffmpegJni_main(JNIEnv *env, jclass obj, jobjectArray commands)
{   
    (*env)->GetJavaVM(env, &jvm); //获取JVM虚拟机
    jclass clazz = (*env)->GetObjectClass(env,obj);  //获取调用此方法的java类
    m_jobj = obj;
    m_clazz = (*env)->NewGlobalRef(env, clazz); //将这个类赋值给m_clazz
    m_env = env; //复制到全局变量m_env
    int argc = (*env)->GetArrayLength(env, commands);
    char *argv[argc];
    int i;
    int result = 0;
    for (i = 0; i < argc; i++)
    {
        jstring js = (jstring)(*env)->GetObjectArrayElement(env, commands, i);
        argv[i] = (char *)(*env)->GetStringUTFChars(env, js, 0);
    }
    LOGD("----------begin---------");
    int ret = main(argc, argv);
    ffmpegJniDone(1);
    return ret;
}


JNIEXPORT jint JNICALL Java_com_chaosgoo_ffmpegproject_ffmpegJni_main(JNIEnv *env, jclass obj, jobjectArray commands){
    (*env)->GetJavaVM(env, &jvm); //获取JVM虚拟机
    jclass clazz = (*env)->GetObjectClass(env,obj);  //获取调用此方法的java类
    m_jobj = obj;
    m_clazz = (*env)->NewGlobalRef(env, clazz); //将这个类赋值给m_clazz
    m_env = env; //复制到全局变量m_env
    int argc = (*env)->GetArrayLength(env, commands);
    char *argv[argc];
    int i;
    int result = 0;
    for (i = 0; i < argc; i++)
    {
        jstring js = (jstring)(*env)->GetObjectArrayElement(env, commands, i);
        argv[i] = (char *)(*env)->GetStringUTFChars(env, js, 0);
    }
    LOGD("----------begin---------");
    int ret = main(argc, argv);
    ffmpegJniDone(1);
    return ret;
}


void callJavaMethod(char *ret)
{
    int ss = 0;
    char *q = strstr(ret, "time=");
    if (q != NULL)
    {
        //LOGE("遇到time=");
        char str[14] = {0};
        strncpy(str, q, 13);
        int h = (str[5] - '0') * 10 + (str[6] - '0');
        int m = (str[8] - '0') * 10 + (str[9] - '0');
        int s = (str[11] - '0') * 10 + (str[12] - '0');
        ss = s + m * 60 + h * 60 * 60;
    }
    else
    {
        return;
    }

    if (m_clazz == NULL)
    {
        LOGE("---------------clazz isNULL---------------");
        return;
    }
    //获取方法ID (I)V指的是方法签名 通过javap -s -public FFmpegCmd 命令生成
    jmethodID methodID = (*m_env)->GetMethodID(m_env, m_clazz, "onProgress", "(I)V");
    if (methodID == NULL)
    {
        LOGE("---------------methodID isNULL---------------");
        return;
    }
     LOGE("---------------Call Method---------------");
    //调用该java方法
    (*m_env)->CallVoidMethod(m_env,m_jobj, methodID, ss);
}

void ffmpegJniDone(int i){
    jmethodID methodID = (*m_env)->GetMethodID(m_env, m_clazz, "onProgress", "(I)V");
    (*m_env)->CallVoidMethod(m_env,m_jobj, methodID, i);
}