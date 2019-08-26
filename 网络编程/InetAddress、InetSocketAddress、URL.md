#### `InetAddress`和`InetSocketAddress`

`InetAddress`封装`IP`和`DNS`，它的构造函数不是公开的，需要通过类提供的静态方法来获取，有以下方法：

```
static InetAddress[] getAllByNames(String host);
static InetAddress getByAddress(byte[] addr)
static InetAddress getByAddress(String host,byte[] addr)
static InetAddress getByName(String host)
static InetAddress getLocalHost()
```

 `InetSocketAddress`基于`InetAddress`构建，封装了端口：

```java
// 构造函数
InetSocketAddress(String hostname, int port);
InetSocketAddress(InetAddress addr, int port);
// 方法
getAddress();
getHostName();
getPort();
```

`URL`类：

```java
// 构造函数
URL url = new URL(网络地址)
URL url = new URL(URL实例，相对地址)
// 方法
url.getProtocol(); // 获取协议
url.getHost(); // 获取主机地址
url.getPort(); // 获取路径端口号
url.getFile(); // 获取地址指向的资源名
url.getPath(); // 获取相对路径
url.getRef(); // 获取路径中锚点
url.getQuery(); // 获取路径中的参数
url.openStream(); // 打开一个到该URL的连接，并返回一个用于从该连接读入的InputStream
```

##### 使用`URL`爬取网页内容并输出到文件：

```java
package com.mengfansheng.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class GetSource {

  public static void main(String[] args) throws IOException {
  URL url = new URL("http://www.baidu.com");
		
  InputStream is = url.openStream();
  BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new  FileOutputStream(new File("E:/baidu.html")), "utf-8"));

  byte[] flush = new byte[1024];
  String msg = null;
  while(null != (msg = br.readLine())){
    bw.append(msg);
    bw.newLine();
  }
  bw.flush();
  bw.close();
  br.close();
  is.close();
  }

}

```

