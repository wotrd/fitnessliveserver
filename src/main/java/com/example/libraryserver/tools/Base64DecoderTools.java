package com.example.libraryserver.tools;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by wkj_pc on 2017/8/31.
 */
public class Base64DecoderTools {
    public static byte[] stringToBytes(String string) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(string);
        for (int i=0;i<bytes.length;++i){
            if (bytes[i]<0){
                //调整异常数据
                bytes[i]+=256;
            }
        }
        return bytes;
    }
}
