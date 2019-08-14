package com.mengfansheng.IO;

import java.io.File;

public class CopyDir {

    public static void main(String[] args){
        copy("E:/GG/mm.txt", "E:/HH");
    }

    /**
     * 文件夹的拷贝
     * 支持拷贝文件到指定文件夹
     * @param source 源文件或文件夹地址
     * @param destination 目标文件夹地址
     */
    public static void copy(String source, String destination){
        copy(new File(source), new File(destination));
    }

    /**
     *
     * @param src 源文件或源文件夹File对象
     * @param dest 目标文件夹File对象
     */
    public static void copy(File src, File dest){
        dest = new File(dest, src.getName()); // 不论拷贝文件还是文件夹，第一步肯定是在目标文件夹中创建源文件或源文件夹同名文件或文件夹
        copyDetail(src, dest);
    }

    public static void copyDetail(File src, File dest){
		if(dest.getAbsolutePath().contains(src.getAbsolutePath())){
            System.out.println("父目录不能拷贝到子目录中");
            return;
        }
         if(src.isFile()){
             CopyFile.copy(src, dest);
         } else {

             dest.mkdirs();
             for(File f:src.listFiles()){
                 copyDetail(f, new File(dest, f.getName()));
             }
         }
    }
}
