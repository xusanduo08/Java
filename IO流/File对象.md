#### 相对路径与绝对路径构造File对象

1.相对路径

File src = File(String parent, String child)  =》File("E:/Users/img", "2.jpg")

Filr src=File(File parent, String child) => File(new File("E:/User/img"), "2.jpg")

Src.getPath() 返回文件的绝对路径

2.绝对路径

File src = File(String name)

src.getPath() 返回文件绝对路径

没有指定盘符，则以工作空间为相对路径构建

File src = new File("test.txt");

src.getPath() => 返回相对路径 =>test.txt

src.getAbsolutePath() => 返回绝对路径



基本信息：

getName() 返回文件名

getParent() 返回父目录名，返回值可能为null

getAbsoluteFile() 返回绝对路径所对应的File对象

getAbsolutePath() 返回绝对路径名

判断方法：

exists() 是否存在

canWrite() 是否可写

canRead() 是否可读

isFile() 是否是文件

isDirectory() 是否是目录

isAbsolute() 是否以绝对路径创建



length() 返回字节长度

createNewFile() 创建文件，如果文件存在则返回false

delete()

mkdir() 创建目录

mkdirs() 创建目录，如果祖先目录不存在的话则一起创建

list() 获取子文件或子目录的名称 返回值为String[]

listFiles() 获取子文件或子目录的File对象 返回值为 File[]

static createTempFile() 创建临时文件

