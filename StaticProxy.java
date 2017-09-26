package com.mengfansheng.Thread;

/*
 * 1、真实角色
 * 2、代理角色
 * 3、代理角色中含有真实角色的引用
 * */

public class StaticProxy {

	public static void main(String[] args) {
		//创建真实角色
		You you = new You();
		
		//创建代理角色
		WeddingCompany we = new WeddingCompany(you);
		//执行任务
		we.marry();
	}

}

interface Marry{
	void marry();
}

class You implements Marry{

	@Override
	public void marry() {
		System.out.println("和嫦娥结婚");
	}
	
}

//代理角色
class WeddingCompany implements Marry{

	private Marry you; //持有真实角色的引用
	
	public WeddingCompany() {
		super();
	}

	public WeddingCompany(Marry you) {
		super();
		this.you = you;
	}

	private void before(){
		System.out.println("布置猪窝");
	}
	private void after(){
		System.out.println("入洞房");
	}

	@Override
	public void marry() {
		before();
		you.marry();
		after();
	}
	
}
