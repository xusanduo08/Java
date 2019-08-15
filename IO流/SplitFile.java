package com.mengfansheng.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.omg.CORBA_2_3.portable.InputStream;

public class SplitFile {

	public static void main(String[] args) throws IOException {

		SplitFile split = new SplitFile("E:/gg/ff.rmvb", 5000000,"E:/gg");
		//split.split();
		//split.mergeFile("E:/gg/merge/gg.rmvb");
		split.merge("E:/gg/merge/ggg.rmvb");
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
	//分割后的路径
	private String destPath;
	
	public SplitFile(){
		blockPath = new ArrayList<String>();
	}
	
	public SplitFile(String filePath,String destPath){
		this(filePath, 1024, destPath);
	}
	
	public SplitFile(String filePath, long blockSize, String destPath){
		this();
		this.filePath = filePath;
		this.blockSize = blockSize;
		this.destPath = destPath;
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
		initPathName();
	}
	
	//计算出每一块名称
	private void initPathName(){
		for(int i = 0; i < this.size; i++){
			this.blockPath.add(this.destPath + "/" + this.fileName + ".part" + i);
		}
	}
	
	/*
	 * 文件分割
	 * 起始位置
	 * 实际大小
	 * */
	public void split() throws IOException{
		long beginPos = 0;
		long actualSize = blockSize;//每块实际大小
		
		for(int i  = 0; i< this.size; i++){
			if(i == this.size - 1){
				actualSize = this.length - beginPos;
			}
			splitDetail(i, beginPos, actualSize);
			beginPos += actualSize;//下一次起点 = 本次起点 + 本次分割的大小
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
		raf.seek(beginPos); // 从beginPos开始读取
		byte[] flush = new byte[1024];
		int len = 0;
		while(-1 != (len = raf.read(flush))){
			
			if(actualSize - len >= 0){ // 如果剩余待分割内容（actualSize）小于flush大小，说明本次只能写入actualSize大小的内容
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
	
	public void mergeFile(String destPath) throws IOException{
		//创建源
		File dest = new File(destPath);
		//选择流
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		bos = new BufferedOutputStream(new FileOutputStream(dest,true));//追加方式写入
		for(int i = 0; i < this.blockPath.size(); i++){
			File blockFile = new File(this.blockPath.get(i));
			if(!blockFile.exists() || blockFile.isDirectory()){
				System.out.println("请先分割文件后合并");
				return;
			}
			bis = new BufferedInputStream(new FileInputStream(new File(this.blockPath.get(i))));
			byte[] flush = new byte[1024];
			int len = 0;
			while(-1 != (len = bis.read(flush))){
				bos.write(flush, 0, len);
			}
			
			FileClose.close( bis);
		}
		
		FileClose.close(bos);
	}
	
	
	public void merge(String destPath) throws IOException{
		Vector<BufferedInputStream> vi = new Vector<BufferedInputStream>();
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destPath),true));
		System.out.println(this.blockPath.size());
		for(int i = 0;i < this.blockPath.size(); i++){
			BufferedInputStream bis = null;
			try {
				File blockFile = new File(this.blockPath.get(i));
				if(blockFile.exists() && !blockFile.isDirectory()){
					bis = new BufferedInputStream(new FileInputStream(blockFile));
					vi.add(bis);
				} else {
					System.out.println("请先进行分割文件操作,然后再进行文件合并");
					return;
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		SequenceInputStream sis = new SequenceInputStream(vi.elements());
		byte[] flush = new byte[1024];
		int len = 0;
		while(-1 != (len = sis.read(flush))){
			bos.write(flush, 0, len);
		}
		bos.flush();
		FileClose.close(sis,bos);
	}
}
