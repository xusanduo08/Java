package com.mengfansheng.rhino;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 测试使用脚本引擎执行javascript代码
 * 
 * **/
public class Rhino01 {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException{
		//获得脚本引擎对象
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		
		//定义变量,存储到引擎上下文
		engine.put("msg", "sanduo is a good man!");
		String str = "var user = {name:'sanduo', age:20, work:'soldier'};";
		str += "print(user.name);";
		
		//执行脚本
		engine.eval(str);
		//存储的变量既可以被js脚本操作，也可以被java操作，也就是说在项目中脚本的运行结果被java获取
		//所以可以使用脚本做一些操作，操作结束后再由java来获取操作结果，这样增加了代码的灵活性
		engine.eval("msg = 'fansheng is a good man!'");
		System.out.println(engine.get("msg"));
		System.out.println("####################################");
		
		//定义js中的函数
		engine.eval("function add(a, b){return a+b;};");
		// 取得调用接口
		Invocable jsInvoke = (Invocable)engine;
		//执行脚本中定义的方法
		Object result = jsInvoke.invokeFunction("add", new Object[]{12, 30});
		System.out.println(result);
		
		//希望在调用脚本语言时获取到java中的一些类和方法，这时候怎么办？
		//java包名+类名+方法调用
		String jsCode = " var list = java.util.Arrays.asList([\"许三多\",\"班长\",\"成才\"]);";
		engine.eval(jsCode);
		
		List<String> list2 = (List<String>)engine.get("list");
		for(String temp:list2){
			System.out.println(temp);
		}
		
		//执行一个外部的js文件
		URL url = Rhino01.class.getClassLoader().getResource("run.js");
		
		FileReader fr = new FileReader(url.getPath());
		engine.eval(fr);
		fr.close();
	}
}
