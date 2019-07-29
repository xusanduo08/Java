package com.mengfansheng.collection;

import java.util.Date;

/**
 * 优化元素的存储
 * Map采用数组+链表存储数据
 */

public class HashMapS {
//    Entry[] arr = new Entry[999];
    LinkedList[] arr = new LinkedList[999];
    int size;

    public void put(Object key, Object value){
        Entry entry = new Entry(key, value);
        int pos = key.hashCode() % arr.length;
        if(arr[pos] == null){
            LinkedList list = new LinkedList();
            list.add(entry);
            arr[pos] = list;
        } else {
            LinkedList list = arr[pos];
            for(int i = 0; i < list.size(); i++){
                Entry e = (Entry)list.get(i);
                if(e.getKey().equals(key)){
                    e.setValue(value);
                    return;
                }
            }
            arr[pos].add(entry); // 没有找到相同键时直接将entry插入链表
        }
    }

    public Object get(Object key){
		// 先通过key找到对应的链表，再遍历链表同时比较每个节点的key，遇到相同的就返回
        int pos = key.hashCode() % arr.length;
        LinkedList list = arr[pos];
        for(int i = 0; i < list.size(); i++){
            Entry entry = (Entry)list.get(i);
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args){
        HashMapS map = new HashMapS();

        map.put("123", "321");
        map.put("123", "323");
        map.put("date", new Date());
        map.put("date", "2019/7/30");


        System.out.println(map.get("123"));
        System.out.println(map.get("date"));
    }
}