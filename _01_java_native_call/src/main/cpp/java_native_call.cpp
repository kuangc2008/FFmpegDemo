#include <jni.h>
#include <string>
#include "android/log.h"

/**
 * 1. JNIEnv*  是一个C++实例，指向JNI函数表的接口指针
 *        提供了function，来使用虚拟机的功能
 * 2  jobject  java对象引用
 *
 * 3  还有一个jclass thiz，如果是静态方法时，参数会是类的类型
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_kc_java_1native_1call_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "native and java call";
    return env->NewStringUTF(hello.c_str());
}


/**
 * java字符串转c字符串
 */
extern "C" JNIEXPORT void JNICALL
Java_com_kc_java_1native_1call_NativeLib_javaToc(
        JNIEnv* env,
        jobject /* this */,
        jstring javaString) {
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","java string : 1");



    const char* str;
    jboolean isCopy;

    str = (*env).GetStringUTFChars(javaString , &isCopy);


    if ( 0 != str) {
        __android_log_print(ANDROID_LOG_DEBUG, "kcc","java string : %s", str);
        if (JNI_TRUE == isCopy) {
            __android_log_print(ANDROID_LOG_DEBUG, "kcc","c string is a copy of the java string");
        } else {
            __android_log_print(ANDROID_LOG_DEBUG, "kcc","c string points to actual string");

        }
    }

    (*env).ReleaseStringUTFChars(javaString, str);

}


extern "C" JNIEXPORT void JNICALL
        Java_com_kc_java_1native_1call_NativeLib_accessField(JNIEnv* env,
                                                             jobject obj) {
    jclass  clazz;
    clazz = (*env).GetObjectClass(obj);

    jfieldID  instanceFieldId;
    instanceFieldId = (*env).GetFieldID(clazz, "b", "I");

    jint instanceB;
    instanceB = reinterpret_cast<jint>((*env).GetIntField(obj, instanceFieldId));

    __android_log_print(ANDROID_LOG_DEBUG, "kcc","instanceB : %d", instanceB);

    jfieldID  staticFieldId;
    staticFieldId = (*env).GetStaticFieldID(clazz, "a", "I");

    jint  staticA;
    staticA = (*env).GetStaticIntField(clazz, staticFieldId);


    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : %d", staticA);



}