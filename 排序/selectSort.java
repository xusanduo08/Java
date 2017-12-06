package com.mengfansheng.javaload;

import java.util.Arrays;

/**
 * 选择排序
 * 第一次选择第0小的放大a[0]位置
 * 第二次选择第1小的放到a[1]位置
 * ...
 * 第N次选择第N小的放到a[N]位置
 * **/
public class selectSort {

	public static void main(String[] args){
		int a[] = {3,2,4,7,5,1};
		int b[] = select(a.length, a);
		System.out.println(Arrays.toString(b));
	}
	
	public static int[] select(int size, int[] a){
		for(int i = 0; i < size - 1; i++){
			int tempMin = i;
			for(int j = i+1; j < size; j++){
				if(a[j] < a [tempMin]){
					tempMin = j;
				}
			}
			int temp = a[i];
			a[i] = a[tempMin];
			a[tempMin] = temp;
		}
		return a;
	}
	
}
