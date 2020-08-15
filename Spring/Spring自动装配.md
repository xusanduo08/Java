### 使用javaConfig进行Spring自动装配

* 如何使用javaConfig对象进行bean对象配置，使用两个注解
  * @Configuration
  * @Bean
* 依赖注入
  * 构造函数
  * setter
  * 任意方法
* 装配过程歧义性

想将第三方库中的组件装配到应用程序中，则没有办法在类上添加@Component和@Autowired等注解

方法：**显式装配 **

* java中进行显式的装配
* XML中进行显示的装配



@Bean：用于告诉Spring，产生一个对象，然后将这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会在启动的时候调用一次，然后Spring会将这个Bean对象放在自己的IOC容器中。

被@Bean注解的方法在调用时会被Spring拦截，spring会检测方法是否已经被调用过（也就是对象是否已经创建），已经调用的话则不会被再次调用。



其他使用UserDao类的依然可以使用@Autowired自动注入。

当然也有其他方法可以注入：

使用构造函数注入：

`UserImpl.java`: 不需要使用@Component注解

```java
public class UserImpl implements UserDao {
  @Override
  public void add() {
    System.out.println("添加用户到数据库。。。。。");
  }
}
```

`UserServiceNormal.ja`：同样不需要@Component注解

```java
public class UserServiceNormal implements UserService {
  private UserDao user;

  public UserServiceNormal(UserDao user) {
    this.user = user; // 通过构造函数注入
  }

  @Override
  public void add() {
    user.add();
  }
}
```

`AppConfig.java`

```java
@ComponentScan(basePackageClasses = {UserController.class})
@Configuration
// 使用@Bean注解的方法会在Spring启动时执行一次，返回的对象会由Spring的IOC容器进行管理
public class AppConfig {
  @Bean
  public UserDao userDaoNormal(){
    return new UserImpl();
  }

  @Bean
  public UserService userServiceNormal(){
    UserDao userDao = userDaoNormal();
    return new UserServiceNormal(userDao); // 通过构造函数注入
  }
	// 或者上面 UserService还可以写的更简洁些
  // Spring在执行这个方法时看到入参是个UserDao，回去IOC容器总去寻找是否有UserDao，有的话自动传入
  @Bean
  public UserService userServiceNormal(UserDao userDao){
    return new UserServiceNormal(userDao);
  }
}
```

使用setter方法注入：

`UserServiceNormal.java`:

```java
public class UserServiceNormal implements UserService {
  private UserDao user;

  public void setUser(UserDao user) {
    this.user = user;
  }

  @Override
  public void add() {
    user.add();
  }
}
```



`AppConfig.java`

```java
@ComponentScan(basePackageClasses = {UserController.class})
@Configuration
public class AppConfig {

  @Bean
  public UserService userServiceNormal(UserDao userDao){
    UserServiceNormal userServiceNormal = new UserServiceNormal();
    userServiceNormal.setUser(userDao); // 调用userServiceNormal的setter方法，将userDao注入进去
    return userServiceNormal;
  }

  @Bean
  public UserDao userDaoNormal(){
    return new UserImpl();
  }
}
```



综上，其实可以实现注入的不一定是构造函数或者setter方法，也可以是其他类似的可以设置对象属性的方法。



使用@Bean时如何解决装配时的歧义性？

* @Primary 声明首选类
* 使用限定符 @Qualifier，声明和装配的时候分别使用@Qualifier
* 使用限定符和 bean id

默认情况下bean id为小写字母开头的方法名

```java
@ComponentScan(basePackageClasses = {UserController.class})
@Configuration
public class AppConfig {

  @Bean
  public UserService userServiceNormal(@Qualifier("cache") UserDao userDao){
    UserServiceNormal userServiceNormal = new UserServiceNormal();
    userServiceNormal.setUser(userDao); // 调用userServiceNormal的setter方法，将userDao注入进去
    return userServiceNormal;
  }

  @Bean
  @Qualifier("normal")
  public UserDao userDaoNormal(){
    return new UserImpl();
  }
  @Bean
  @Qualifier("cache")
  public UserDao userDaoCache(){
    return new UserCache();
  }
}
```



