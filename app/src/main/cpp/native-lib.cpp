#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_arc_secureapikotlin_MainActivity_BASEURL(
        JNIEnv* env,
        jobject /* this */) {
    std::string api_key = "https://api.learn2crack.com/android/jsonandroid/";
    return env->NewStringUTF(api_key.c_str());
}
