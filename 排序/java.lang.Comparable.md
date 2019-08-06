#### java.lang.Comparable

实现该接口的类具有`compareTo`方法用以比较。

Integer：按数值大小比价

Char：按字符的Unicode码比较大小

String：其中一个字符串是另一个从头开始的字串，则返回长度之差，否则返回第一个不想等的字符的Unicode码之差

Date：比较日期长整型数



```java
package com.mengfansheng.sort;

import java.util.List;

// 一个排序工具方法
public class SortUtil {

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

}
```

