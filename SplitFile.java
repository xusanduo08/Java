package com.mengfansheng.IO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SplitFile {

	public static void main(String[] args) throws IOException {

		SplitFile split = new SplitFile("E:/Git/ww.rmvb", 500000);
		split.split("E:/Git");
	}

	//文件路径
	private String filePath;
	//文件大小
	private long length;
	
	//文件名
	private String fileName;
	//块数
	private int size;
	//每一块大小
	private long blockSize;
	//每一块名称
	private List<String> blockPath;
	
	public SplitFile(){
		blockPath = new ArrayList<String>();
	}
	
	public SplitFile(String filePath){
		this(filePath, 1024);
	}
	
	public SplitFile(String filePath, long blockSize){
		this();
		this.filePath = filePath;
		this.blockSize = blockSize;
		init();
	}
	
	
	//初始化，计算块数、确定文件名
	public void init(){
		File src = null;
		if(null == filePath || !((src = new File(filePath)).exists())){
			return;
		}
		if(src.isDirectory()){
			return;
		}
		
		//文件名称
		this.fileName = src.getName();
		//计算块数 实际大小 每一块大小
		this.length = src.length();
		//修正 每块大小
		if(this.blockSize > length){
			this.blockSize = length;
		}
		//确定块数
		this.size = (int)Math.ceil(length*1.0 / this.blockSize);
	}
	
	//每一块名称
	private void initPathName(String destPath){
		for(int i = 0; i < this.size; i++){
			this.blockPath.add(destPath + "/" + this.fileName + ".part" + i);
		}
	}
	
	/*
	 * 文件分割
	 * 起始位置
	 * 实际大小
	 * */
	public void split(String dest) throws IOException{
		long beginPos = 0;
		long actualSize = blockSize;//每块实际大小
		initPathName(dest);
		for(int i  = 0; i< this.size; i++){
			if(i == this.size - 1){
				actualSize = this.length - beginPos;
			}
			splitDetail(i, beginPos, actualSize);
			beginPos += actualSize;//下一次起点
		}
	}
	
	private void splitDetail(int index, long beginPos, long actualSize) throws IOException{
		//创建源
		File src = new File(this.filePath);
		File dest = new File(this.blockPath.get(index));
		//选择流
		RandomAccessFile raf;//输入流
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));//输出流
		raf = new RandomAccessFile(src,"r");
		raf.seek(beginPos);
		byte[] flush = new byte[1024];
		int len = 0;
		while(-1 != (len = raf.read(flush))){
			
			if(actualSize - len >= 0){
				bos.write(flush, 0 , len);
				actualSize -= len;
			} else {
				bos.write(flush, 0 , (int)actualSize);
				break;
			}
		}
		bos.close();
		raf.close();
	}
	
}
