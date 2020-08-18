### 使用XML配置

`<bean id="printer" name="printer" class="全路径包名" />`

id和name在整个xml文件中都需要是唯一的。

name中可以使用空格 分号 逗号 分隔设置别名，而id则不可以。

`<bean id="printer" name="printer printer1 printer2" />`



```xml
<bean id="service" class="MessageService"></bean>
<bean id="printer" class="MessagePrinter">
  <property name="service" ref="service"></property>
</bean>
```

```java
// 如果geBean参数不是一个class的话，则结果要进行一下强转
// getBean参数可以是class类，可以是xml中定义的bean的id，也可以是name
MessagePrinter printer = (MessagePrinter) context.getBean("printer");
```

ref所要注入的其他类的id，上面`<property>`的作用就是向MessagePrinter中注入id为service的类。



`<constructor-arg ref="" />`：通过构造函数注入

要有满足条件的有参构造函数。

```xml
<bean id="printer" class="MessagePrinter">
  <constructor-arg ref="service" />
</bean>
```



另外可以使用c名称空间注入。

