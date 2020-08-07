//
// Created by 王者财经 on 2016/10/26.
//
#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <stdio.h>

char* bitEncode(char*data, int orgLen, char*key, int keyLen) {
    if(keyLen <= 0 ||	orgLen <= 0 )
        return data;
    int i;
    int k;
    char c;
    int temp;
    for (i = 0; i < orgLen; i++) {
        k = i % keyLen;
        c = data[i];
        temp = (key[k / 3] + key[k / 2] + key[k] + key[keyLen - k - 1]) % 4 + 1;
        c = (c << (8 - temp));
        data[i] = data[i] ^ c;
    }
    return data;
}
jbyteArray Java_com_iwangzhe_commonlibs_mod_tool_jni_JniEncryptUtil_encrypts
        (JNIEnv *env, jobject obj, jbyteArray data, jint orgLen, jbyteArray key, jint keyLen){
    char * c=bitEncode((char *)(*env)->GetByteArrayElements(env,data, 0),orgLen,(char *)(*env)->GetByteArrayElements(env,key, 0),keyLen);
    jbyteArray jarray1 =(*env)->NewByteArray(env,orgLen);
    (*env)->SetByteArrayRegion(env,jarray1, 0, orgLen, c);
    return jarray1;
}