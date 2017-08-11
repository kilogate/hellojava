#include "jni.h"
#include "com_kilogate_hello_java_javase_jdkapi_jni_HelloNative.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_com_kilogate_hello_java_javase_jdkapi_jni_HelloNative_greeting(JNIEnv *env, jclass clazz){
    printf("Hello Native!\n");
    return;
}
