package com.mengfansheng.Thread;

/*
 * 生产者
 * */

public class Player implements Runnable {
	private Movie m;
	public static void main(String[] args) {

	}
	
	public Player(Movie m) {
		super();
		this.m = m;
	}

	public void run(){
		for(int i = 0; i< 20; i++){
			if(0 == i%20){
				m.play("左青龙");
			} else {
				m.play("右白虎");
			}
		}
	}

}
