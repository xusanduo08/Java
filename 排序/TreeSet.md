#### TreeSet：
两种使用情况：
1.实体类可以排序（实现了Comparable接口）
2.提供排序比较器。 `public TreeSet(Comparator<? super E> comparator)`

TreeSet在添加元素就进行了排序

在添加之后再修改数据不会影响原来的排序。

而且数据添加后不要修改数据，否则可能出现重复数据。

为了避免数据添加后又被修改，可以将待排序类的属性设置为`final`。

看下面一个用`TreeSet`排序的例子，用到了`Comparator`接口，提供了排序比较器：

```java
package com.mengfansheng.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class Person {
  private final String name; // final修饰，初始化后就不允许修改，避免加入TreeSet后又被修改
  private final int handSome;

  public Person(){
    this.name = null;
    this.handSome = 0;
  }

  public Person(String name, int handSome){
    this.name = name;
    this.handSome = handSome;
  }

  public String getName() {
    return name;
  }

  public int getHandSome() {
    return handSome;
  }

  public String toString(){
    return "name: "+this.name + ", handSome: "+this.handSome;
  }

  public static void main(String[] args){
    TreeSet<Person> set = new TreeSet<>(
      new Comparator<Person>() {
         @Override
         public int compare(Person o1, Person o2) {
            return o1.getHandSome() - o2.getHandSome();
         }
      }
    );
    set.add(new Person("xiaoming", 20));
    set.add(new Person("xiaoxiao", 21));
    set.add(new Person("mingming", 30));
    set.add(new Person("hong", 37));

    System.out.println(Arrays.toString(set.toArray()));
  }
}

```

如果待排序类实现了`Comparable`接口，那么可以直接放入到TreeSet中进行排序。


