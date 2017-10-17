package com.mengfansheng.Thread;

public class Watcher implements Runnable{
	private Movie m;
	public static void main(String[] args) {
		
	}
	
	public Watcher(Movie m) {
		super();
		this.m = m;
	}

	public void run(){
		for(int i = 0; i< 20; i++){
			m.watch();
		}
	}

}
