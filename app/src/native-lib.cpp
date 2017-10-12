#include <jni.h>
#include <string>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <malloc.h>
#include <android/log.h>


void ReadFile(char* fileName, char* outString)
{
    FILE *fp = fopen(fileName, "r");
    try {
        if (fp != NULL) {
            size_t i = 0;
            char c;
            while ((c = fgetc(fp)) != EOF) {
                outString[i++] = c;

            }
            outString[i] = '\0';
            fclose(fp);

        }
    }
    catch (std::exception ex)
    {
        fclose(fp);
        throw ex;
    }

}

extern "C"
JNIEXPORT jstring JNICALL

Java_com_example_brengarajulu_distracteddriving_MainActivity_ProcessSensorData(
        JNIEnv *pEnv, jobject  thiz, jstring cmd, jstring jpath
        )
{
    const char * text =pEnv->GetStringUTFChars(cmd,0);
    const char * path =pEnv->GetStringUTFChars(jpath,0);
    char result[100];
    char fName[64];
    strcpy(fName,path);
    strcat(fName,"/myfile.txt");
    FILE* fp;
    try {
    fp= fopen(fName, "w+");
    fputs("Distraction Detected",fp);
    fflush(fp);
    fclose(fp);
    }
    catch(std::exception ex){
        throw ex;
    }
    ReadFile(fName,result);
    jstring str = pEnv->NewStringUTF(result);
    return str;


}



