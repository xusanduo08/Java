#### Iterator接口

* 所有实现了Collection接口的容器类都有一个iterator方法用以返回一个实现了Iterator接口的对象
* Iterator对象称作迭代器，用以方便的实现对容器内元素的遍历操作

Iterator接口定义了如下方法：

```java
boolean hasNext(); // 判断是否有元素没有被遍历
Object next();	// 返回游标当前位置的元素并将游标移动到下一个位置
void remove(); // 删除游标左面的元素，在执行完next之后，该操作只能执行一次
```

