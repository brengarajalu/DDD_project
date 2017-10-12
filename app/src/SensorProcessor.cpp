#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_brengarajulu_distracteddriving_MainActivity_ProcessMySensorData1(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Processing Mobile Sensor Data";
    return env->NewStringUTF(hello.c_str());
}

