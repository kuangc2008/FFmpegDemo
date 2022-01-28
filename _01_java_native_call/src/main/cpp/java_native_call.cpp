#include <jni.h>
#include <string>

/**
 * 1. JNIEnv*
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_kc_java_1native_1call_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "native and java call";
    return env->NewStringUTF(hello.c_str());
}