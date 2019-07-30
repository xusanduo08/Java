#### equals和hashCode

`equals()`用来比较两个对象是否相等。在源码中，通过判断两个对象的地址是否相等来区分它们是否相等。源码如下：

```java
public boolean equals(Object obj){
	return (this == obj);
}
```

所以，默认的`equals()`方法，等价于`==`方法。因此，我们通常会重写`equals()`方法：若两个对象的内容相等，则`equals()`方法返回`true`；否则，返回`false`。

如果类没有覆盖`equals()`方法，则当用`equals()`比较两个对象时，实际上是比较两个对象是不是同一个对象，此时等价于通过`==`去比较这两个对象。

如果类覆盖了`equals()`方法，通常的做法是：若两个对象的内容相等，则`equals()`返回`true`；否则，返回`false`。



`hashCode()`的作用是__获取哈希码__，也称为散列码。它实际上返回一个`int`整数，这个哈希码的作用是确定该对象在哈希表中的索引位置。

`hashCode()`定义在JDK的Object.java中，也就是说Java中的任何类都包含有`hashCode()`函数。

如上所说，`hashCode()`用来确定对象在散列表中的位置，所以说创建某个类的散列表时，该类的`hashCode()`才有用。

##### hashCode()和equals()的关系

第一种情况：

如果某个类不会被用到散列表（`HashSet`，`HashTable`，`HashMap`等）结构中，那么，该类的`hashCode()`和`equals()`没有一丁点关系。

这种情况下，`equals()`用来比较该类的两个对象是否相等，而`hashCode()`则根本用不到，所以这种情况下，不用理会`hashCode()`。

第二种情况：

某个类会被用到散列表（`HashSet`，`HashTable`，`HashMap`等）结构中（被用来做键），这种情况下，`hashCode()`和`equals()`是有关系的：

* 如果两个对象通过`equals()`比较返回`true`，那么它们的`hashCode()`值一定相同
* 如果两个对象的`hashCode()`相等，则`equals()`并不一定返回`true`。（存在不同对象hash值相同的情况，这种情况叫做__hash冲突__）

在这种情况下，重写对象的`equals()`方法，一定要重写`hashCode()`方法，否则`equals()`无效。

如果重写`equals()`却没有重写`hashCode()`方法，那么在散列表中，就有可能出现相同键值的的数据。