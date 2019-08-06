#### java.lang.Comparable，java.util.Comparator，Collections

实现Comparable接口的类具有`compareTo()`方法用以比较，比如以下几个内置对象都实现了Comparable接口，都有`compareTo()`方法，各自的比较逻辑如下：

Integer：按数值大小比价

Char：按字符的Unicode码比较大小

String：其中一个字符串是另一个从头开始的字串，则返回长度之差，否则返回第一个不想等的字符的Unicode码之差

Date：比较日期长整型数



Comparator接口可根据业务规则自定义排序方法，实现该接口的类需要重写`compare()`方法。



```java
package com.mengfansheng.sort;

import java.util.List;


public class SortUtil {
  // 使用实现Comparable接口的对象自身的compareTo()方法排序
  public static<T extends Comparable<T>> void sort(T[] obj){

    for(int i = obj.length - 1; i > 0; i--){
      for(int j = 0; j < i; j ++){
        if(obj[j].compareTo(obj[j+1]) > 0){
          T tmp = obj[j];
          obj[j] = obj[j+1];
          obj[j+1] = tmp;
        }
      }
    }
  }
  public static void sort(Object[] arr){
    for(int i = arr.length - 1; i > 0; i--){
      for(int j = 0; j < i; j ++){
        if(((Comparable)arr[j]).compareTo(arr[j+1]) > 0){
          Object tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
        }
      }
    }
  }
  
  // 容器排序
  public static <T extends Comparable> void sort(List<T> list){
    Object[] arr = list.toArray(); // 转成数组
    sort(arr);
    for(int i = 0; i < list.size(); i++){
      list.set(i, (T)arr[i]); // 改变容器里的值
    }
  }
    
  // 使用实现Comparator接口的自定义排序器实现排序
  public static <T> void sort(T[] arr, Comparator<T> com){
    for(int i = arr.length - 1; i > 0; i--){
      for(int j = 0; j < i; j++){
        if(com.compare(arr[j], arr[j+1]) > 0){
          T tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
         }
       }
      }
    }
}
```



Collections 工具类提供了大量便于处理容器的方法，关于排序有以下两个：

* `public static <T extends Comparable<? super T>> void sort(List<T> list)`
* `public static <T> void sort (List<T> list, Comparator<? super T> c)`

我们上面实现的两个方法和Collections提供的这两个方法基本类似，分别都是：一个是使用类自身的`compareTo()`方法排序，一个是使用自定义实现Comparator接口的业务类排序。



总结一下，内置的引用类型排序方式大致就是上面两种：

1.使用类自身的比较方法`compareTo()`，实现自`java.lang.Comparable`接口

2.自定义业务类，使用业务类`compare()`方法排序，实现自`java.util.Comparator`接口。使用排序业务类的好处就是排序方法与实体类分离，保证实体类完整性，并且业务类能很方便应对多变的排序规则（多个规则就创建多个排序业务类）

自定义类型可以选择上面任意一种进行排序。