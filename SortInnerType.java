package com.mengfansheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SortInnerType {

	public static void main(String[] args){
		String[] arr = {"a", "abc", "bcf", "ace"};
		
		sort(arr);
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("abc");
		list.add("bcf");
		list.add("ace");
		sort(list);
		
	}
	
	public static <T extends Comparable<T>> void sort(List<T> list){ //方法重载，可以对List进行排序
		Object[] arr = list.toArray();
		boolean sorted = true;
		int len = arr.length;
		for(int j = 0; j < len; j++){
			sorted = true;
			for(int i = 0; i< len - 1 - j; i++){
				if(((Comparable)arr[i]).compareTo(arr[i+1])>0){ //调用Comparable接口中的CompareTo方法
					Object temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					sorted = false;
				}
			}
			if(sorted){
				break;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
	/*
	 * 方法可以对Integer/Character/String/Date等实现Comparable接口的内置类进行排序
	 * */
	
	public static <T extends Comparable<T>> void sort(T[] arr){  //使用泛型
		boolean sorted = true;
		int len = arr.length;
		for(int j = 0; j < len; j++){
			sorted = true;  	//减少比较的趟数
			for(int i = 0; i< len - 1 - j; i++){ 	//减少每趟比较次数
				if(((Comparable)arr[i]).compareTo(arr[i+1])>0){
					T temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					sorted = false;
				}
			}
			if(sorted){
				break;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
}
