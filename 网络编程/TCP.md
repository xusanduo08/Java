#### TCP

TCP的实现通过`Socket`（客户端）和`ServerSocket`（服务器端）两个类来完成。

> Socket又称套接字，应用程序通常通过套接字向网络发出请求或者应答网络请求
>
> Socket是建立网络连接时使用的，在连接成功时，__应用程序两端都会产生一个socket实例。操作这个实例，完成所需会话__。
>
> Socket允许程序员将网络连接看作是另外一个可以读写字节的流。

TCPServer.java:

```java
public static void TCPServer(){
  // 创建服务端 指定端口号
  ServerSocket server = new ServerSocket(8888);
  Socket socket = server.accept(); // 等待连接，阻塞，连接建立后才会继续执行后序代码
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
  bw.write("连接成功");
  bw.newLine();
  bw.flush();
}
```

TCPClient.java

```java
public static void TCPClient(){
   // 创建客户端 指定地址+端口号
  Socket socket = new Socket("localhost", 8888);
  BufferedInputStream bis = new BufferedInputStream(new InputStreamReader(socket.getInputStream()));
  System.out.println(bis.readLine());
}
```

