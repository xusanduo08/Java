#### Iterator接口:`java.util.Iterator`

* 所有实现了Collection接口的容器类都有一个iterator方法用以返回一个实现了Iterator接口的对象
* Iterator对象称作迭代器，用以方便的实现对容器内元素的遍历操作

Iterator接口定义了如下方法：

```java
boolean hasNext(); // 判断是否有元素没有被遍历
Object next();	// 返回游标当前位置的元素并将游标移动到下一个位置
void remove(); // 删除游标左面的元素，在执行完next之后，该操作只能执行一次
```



所有可以使用增强for遍历的对象都实现了`java.lang.Iterable`接口，实现这个接口需要重写的放法为`Iterator<T> iterator()`。



```java
private class Iterator implements java.util.Iterator<E>{
	private cursor = 0;
	private int lastRet = -1;
	public Object next(){
    Object obj = get(cursor);
    lastRet = cursor;
    cursor++;
    
		return obj;
	}
	public boolean hasNext(){
		return cursor != size; 
	}
	public void remove(){
		if(lastRet == -1){
      try{
        throw new Error("已经删除过了");
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    LinkedList.this.remove(lastRet);
    cursor--; // 删除之后要将游标左移一个
    lastRet = -1;
	}
}

public java.util.Iterator iterator(){
  return new Iterator();
}
```

