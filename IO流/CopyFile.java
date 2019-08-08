package com.mengfansheng.IO;

import java.io.*;

public class CopyFile {
    public static void main(String[] args){
        copy("E:/GG/ss.txt", "E:/GG/mm.txt");
    }
    public static void copy(File src, File dest){
        InputStream is = null;
        OutputStream os = null;

        if(!src.exists()){
            System.out.println("file does not exist");
            return;
        }
        if(src.isDirectory()){
            System.out.println("请传入一个文件");
            return;
        }
        byte[] flush = new byte[1024];
        int len = 0;
        try{
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            while(-1 != (len = is.read(flush))){ // 读取+写出
                os.write(flush, 0, len);
            }
            os.flush(); // 强制刷出
        } catch(FileNotFoundException e){
            System.out.println("file does not exist");
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            // 习惯是先打开的后关闭
            if(os != null){
                try{
                    os.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(is != null){
                try{
                    is.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void copy(String source, String destination){
        copy(new File(source), new File(destination));
    }
}
