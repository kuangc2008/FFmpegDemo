#include <jni.h>
#include "android/log.h"
#include "../render/MyGLRenderContext.h"

//
// Created by qihoo on 2022/4/29.
//



extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnInit(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "kcc","onInit: 1");

    MyGLRenderContext::GetInstance();


}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnUnInit(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","onUnInit: 1");

    MyGLRenderContext::DestroyInstance();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1SetImageData(JNIEnv *env, jobject thiz,
                                                                jint format, jint width,
                                                                jint height,
                                                                jbyteArray imageData) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","setImageData: 1");

    int len = env->GetArrayLength (imageData);
    uint8_t* buf = new uint8_t[len];
    env->GetByteArrayRegion(imageData, 0, len, reinterpret_cast<jbyte*>(buf));
    MyGLRenderContext::GetInstance()->SetImageData(format, width, height, buf);
    delete[] buf;
    env->DeleteLocalRef(imageData);

}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnSurfaceCreated(JNIEnv *env, jobject thiz) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","SurfaceCreated: 1");

    MyGLRenderContext::GetInstance()->OnSurfaceCreated();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnSurfaceChanged(JNIEnv *env, jobject thiz,
                                                                    jint width, jint height) {

    __android_log_print(ANDROID_LOG_DEBUG, "native","surfaceChanged: 1");
    MyGLRenderContext::GetInstance()->OnSurfaceChanged(width, height);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_kc_opengles_MyNativeRender_native_1OnDrawFrame(JNIEnv *env, jobject thiz) {

//    __android_log_print(ANDROID_LOG_DEBUG, "native","drawFrame: 1");

    MyGLRenderContext::GetInstance()->OnDrawFrame();
}


static JNINativeMethod g_RenderMethods[] = {
        {"native_OnInit",             "()V",       (void *)(native_OnInit)},
        {"native_OnUnInit",           "()V",       (void *)(native_OnUnInit)},
        {"native_SetImageData",       "(III[B)V",  (void *)(native_SetImageData)},
        {"native_OnSurfaceCreated",   "()V",       (void *)(native_OnSurfaceCreated)},
        {"native_OnSurfaceChanged",   "(II)V",     (void *)(native_OnSurfaceChanged)},
        {"native_OnDrawFrame",        "()V",       (void *)(native_OnDrawFrame)},
};

static int RegisterNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *methods, int methodNum)
{
    LOGCATE("RegisterNativeMethods");
    jclass clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        LOGCATE("RegisterNativeMethods fail. clazz == NULL");
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, methods, methodNum) < 0)
    {
        LOGCATE("RegisterNativeMethods fail");
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static void UnregisterNativeMethods(JNIEnv *env, const char *className)
{
    LOGCATE("UnregisterNativeMethods");
    jclass clazz = env->FindClass(className);
    if (clazz == NULL)
    {
        LOGCATE("UnregisterNativeMethods fail. clazz == NULL");
        return;
    }
    if (env != NULL)
    {
        env->UnregisterNatives(clazz);
    }
}

// call this func when loading lib
extern "C" jint JNI_OnLoad(JavaVM *jvm, void *p)
{
    LOGCATE("===== JNI_OnLoad =====");
    jint jniRet = JNI_ERR;
    JNIEnv *env = NULL;
    if (jvm->GetEnv((void **) (&env), JNI_VERSION_1_6) != JNI_OK)
    {
        return jniRet;
    }

    jint regRet = RegisterNativeMethods(env, NATIVE_RENDER_CLASS_NAME, g_RenderMethods,
                                        sizeof(g_RenderMethods) /
                                        sizeof(g_RenderMethods[0]));
    if (regRet != JNI_TRUE)
    {
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}

extern "C" void JNI_OnUnload(JavaVM *jvm, void *p)
{
    JNIEnv *env = NULL;
    if (jvm->GetEnv((void **) (&env), JNI_VERSION_1_6) != JNI_OK)
    {
        return;
    }

    UnregisterNativeMethods(env, NATIVE_RENDER_CLASS_NAME);
}
