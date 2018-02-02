#include <jni.h>
#ifndef _Included_org_mqstack_ffmpegjni_FFmpegJni
#define _Included_org_mqstack_ffmpegjni_FFmpegJni
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_com_chaosgoo_mixmusic_ffmpegJni_main
        (JNIEnv *env, jclass obj, jobjectArray commands);
JNIEXPORT jint JNICALL Java_com_chaosgoo_ffmpegproject_ffmpegJni_main
        (JNIEnv *env, jclass obj, jobjectArray commands);
void callJavaMethod(char *ret);
void ffmpegJniDone(int i);
#ifdef __cplusplus
}
#endif
#endif