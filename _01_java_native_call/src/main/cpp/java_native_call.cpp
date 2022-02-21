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


extern "C" JNIEXPORT void JNICALL
Java_com_kc_java_1native_1call_NativeLib_accessMethod(JNIEnv* env,
                                                     jobject obj) {
    jclass  clazz;
    clazz = (*env).GetObjectClass(obj);

    jmethodID instanceMethodId;


    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 1111");
    instanceMethodId = (*env).GetMethodID(clazz, "onCallBb", "()I");
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 2222");

    jint instanceMethodResult;
    instanceMethodResult = (*env).CallIntMethod(obj, instanceMethodId);
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 33333");
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","static result : %d", instanceMethodResult);




    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 4444");
    instanceMethodId = (*env).GetMethodID(clazz, "onCallCC", "(Ljava/lang/String;)Ljava/lang/String;");
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 5555");

    jstring stringResult;
    jstring aaa = env->NewStringUTF("lalalalalal");;
    stringResult = static_cast<jstring>((*env).CallObjectMethod(obj, instanceMethodId, aaa));
    __android_log_print(ANDROID_LOG_DEBUG, "kcc","staticA : 6666");

    const char* str;
    jboolean isCopy;

    str = (*env).GetStringUTFChars(stringResult , &isCopy);
    if ( 0 != str) {
        __android_log_print(ANDROID_LOG_DEBUG, "kcc","static result : %s", str);
        if (JNI_TRUE == isCopy) {
            __android_log_print(ANDROID_LOG_DEBUG, "kcc","c string is a copy of the java string");
        } else {
            __android_log_print(ANDROID_LOG_DEBUG, "kcc","c string points to actual string");

        }
    }

    (*env).ReleaseStringUTFChars(stringResult, str);
}





extern "C" JNIEXPORT void JNICALL
Java_com_kc_java_1native_1call_NativeLib_exceptionDeal(JNIEnv* env,
                                                      jobject obj) {
    jthrowable  ex;


    jclass  clazz;
    clazz = (*env).GetObjectClass(obj);

    jmethodID throwMethodId;
    throwMethodId = (*env).GetMethodID(clazz, "throwingMethod", "()V");

    (*env) . CallVoidMethod(obj, throwMethodId);

    // 如果不捕获，则有空指针，会直接退出
    ex = (*env) . ExceptionOccurred();

    if (0 != ex) {
        (*env) . ExceptionClear();

        __android_log_print(ANDROID_LOG_DEBUG, "kcc","Exception handle ");
    }
}