package com.mengfansheng.net;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
    public static void closeAll (Closeable... close){
        for(Closeable tmp: close){
            try {
                tmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
