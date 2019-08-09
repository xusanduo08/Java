#### IO流

IO流分类：

1.按流向分：输入流（流向程序）、输入流（流出程序）

2.按数据类型分：字节流：二进制，可以处理一切文件

​    				  字符流：文本文件，只能处理纯文本

3.按功能分：节点流：离源头最近的那个，包裹源头

​					   处理流：增强功能，提高性能



##### 字符流与字节流

字节流：

* 输入流：InputStream（抽象类，包含有read(byte[] b)/read(byte[] b, int off, int len)等方法）、close()
  * 实现类：FileInputStream
* 输出流：OutputStream（抽象类，包含有write(byte[] b)/write(byte[] b, int off, int len)等方法）、flush()、close()
  * 实现类：FileOutputStream

字符流：

* 输入流：Reader（抽象类，包含有read(char[] cbuf)/read(char[] cbuf, int off, int len)等方法）、close()
  * 实现类FileReader
* 输出流：Writer（抽象类，包含有write(char[] cbuf)/write(char[] cbuf, int off, int len)等方法）、flush()、close()
  * 实现类FileWriter

读取文件时的步骤：

* 建立与文件的联系
* 选择对应流
* 开始操作-读取或者写出
  * 选择数组大小
  * 读取或者写出
* 操作结束，释放资源