#### TreeMap:根据key来排序

两种使用情况：

1.确保key可以排序（key对象实现了Comparable接口）

2.提供排序比较器。`public TreeMap(Comparator<? super K> comparator)`