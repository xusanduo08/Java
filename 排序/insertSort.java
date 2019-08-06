package com.mengfansheng.javaload;

import java.util.Arrays;

/**
 * 数组分为有序部分和无序部分，有序部分在左，无序部分在右
 * 一开始，有序部分只有a[0]，剩下的都是无序部分
 * 遍历无序部分，插入到有序部分合适的位置
 * */
public class insertSort {

	public static void main(String[] args){
		int a[] = {3,2,4,7,5,1};
		int b[] = insert(a.length, a);
		System.out.println(Arrays.toString(b));
		String str;
	}
	
	public static int[] insert(int size, int[] a){
		for(int i = 1; i < size; i++){
			for(int j = 0; j < i; j++){
				if(a[i] < a[j]){
					int temp = a[i];
					for(int k = i; k>j; k--){//将j（包含j）位置到i（包含i）位置的数据往后移一位
						a[k] = a[k - 1];
					}
					a[j] = temp;
					break;
				}
			}
		}
		return a;
		
	}
	
}
