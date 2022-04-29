#include <jni.h>
#include "android/log.h"

//
// Created by qihoo on 2022/4/29.
//


extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnInit(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "kcc","onInit: 1");

}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnUnInit(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","onUnInit: 1");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1SetImageData(JNIEnv *env, jobject thiz,
                                                                jint format, jint width,
                                                                jint height,
                                                                jbyteArray byte_array) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","setImageData: 1");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnSurfaceCreated(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","SurfaceCreated: 1");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnSurfaceChanged(JNIEnv *env, jobject thiz,
                                                                    jint width, jint height) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","surfaceChanged: 1");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnDrawFrame(JNIEnv *env, jobject thiz) {

//    __android_log_print(ANDROID_LOG_DEBUG, "native","drawFrame: 1");
}