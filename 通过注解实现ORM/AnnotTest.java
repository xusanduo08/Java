package com.mengfansheng.annotation;

@Annot01(name = "", schools = { "" })
public class AnnotTest {
	
	@Annot01(age=19, name="小明",id=1001,schools={"北大", "清华"})
	public void test(){
		
	}
	
	@Annot02("aa")
	public void test2(){
		
	}
}
