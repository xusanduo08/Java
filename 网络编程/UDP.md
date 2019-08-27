#### UDP

UDP ：以数据为中心，无连接服务

类：`DatagramSocket`  

封装数据包类：`DatagramPacket`

客户端的创建：

1. 创建客户端：`DatagramSocket`+指定发送端口
2. 准备数据：字节数组
3. 打包：`DatagramPacket` + 服务器地址及服务器接收端口
4. 发送
5. 释放资源

服务器端创建：

1. 创建服务端 ：`DatagramSocket` + 指定端口
2. 准备接收容器：字节数组
3.  封装`Datagrampacket`
4. 接收包，接收数据，分析
5. 释放资源



UDP 客户端：

```java
public static void UDPClient() throws IOException{
  // 创建客户端，指定发送端口为6666
  DatagramSocket client = new DatagramSocket(6666);
  // 准备发送的数据
  String msg = "UDP通信";
  byte[] data = msg.getBytes();
  // 封装数据包，指定服务器端口为 8888
  DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
  // 发送数据
  client.send(packet);
  // 关闭
  client.close();
}
```
UDP 服务器端：
```java
public static void UDPServer() throws IOException{
  // 创建服务器端，接收数据端口为8888
  DatagramSocket server = new DatagramSocket(8888);
  // 创建容器
  byte[] container = new byte[1024];
  // 封装数据包
  DatagramPacket packet = new DatagramPacket(container, container.length);
  // 接收数据
  server.receive(packet);
  // 分析数据
  byte[] data = packet.getData();
  int len = packet.getLength();
  System.out.print(new String(data, 0, len));
}
```

如何发送和接收带有类型的数据？使用`DataOutputStream`和`DataInputStream`

比如发送和接收一个`double`类型的数据，发送的数据使用下面的方法处理：

```java
public static byte[] convert(double num){
  ByteArrayOutputStream bos = new ByteArrayOutputStream();
  DataOutputStream dos = new DataOutputStream(bos);
  try {
    dos.writeDouble(num);
  } catch (IOException e) {
    e.printStackTrace();
  }finally{
    try {
      dos.flush();
    } catch (IOException e) {
       e.printStackTrace();
    }
  }
  return bos.toByteArray();
}
```

接收的数据使用下面的方法处理：

```java
public static double receive(byte[] data){
  ByteArrayInputStream bis = new ByteArrayInputStream(data);
  DataInputStream dis = new DataInputStream(bis);
  double num = 0;
  try {
    num = dis.readDouble();
  } catch (IOException e) {
    e.printStackTrace();
  } finally{
    try{
      dis.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }
  return num;
}
```



