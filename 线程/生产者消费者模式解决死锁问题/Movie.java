package com.mengfansheng.Thread;

/*
 * 生产者消费者模式  信号灯法
 * wait():等待， 释放锁  sleep(): 不释放锁
 * notify()/notifyAll():唤醒
 * */
public class Movie {

	private boolean flag = true;
	/*
	 * flag == true =>生产者生产，消费者等待，生产完成后通知消费者消费
	 * flag == false =>消费者消费，生产者等待，消费完成后通知生产者生产
	 * */

	private String pic;
	
	public synchronized void play(String pic){
		
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//模拟生产
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//生产完毕
		this.pic = pic;
		//通知消费
		this.notify();
		//生产者停下
		this.flag = false;
	}
	
	public synchronized void watch(){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//模拟消费
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//消费完毕
		System.out.println(pic);
		//通知生产
		this.notify();
		//消费者停下
		this.flag = true;
	}
}
