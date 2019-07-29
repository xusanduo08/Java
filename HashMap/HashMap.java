package com.mengfansheng.collection;

import java.util.Date;


/**
 * 删除、查找、获取都要进行遍历操作，如果元素很多的话，操作费时
 */
public class HashMap  {

    Entry[] arr = new Entry[990];
    int size;

    public void put(Object key, Object value){
        for(int i = 0; i < size; i++){
            if(arr[i].getKey().equals(key)){
                arr[i].setValue(value);
                return;
            }
        }
        Entry entry = new Entry(key, value);
        arr[size++] = entry;
    }

    public Object get(Object key){
        for(int i = 0; i < size; i++){
            if(arr[i].getKey().equals(key)){
                return arr[i].getValue();
            }
        }
        return null;
    }

    public void remove(Object key){
        for(int i = 0; i < size; i++){
            if(arr[i].getKey().equals(key)){
                System.arraycopy(arr, i + 1, arr, i, size - i - 1);
                size--;
            }
        }
    }

    public boolean containsKey(Object key){
        for(int i = 0; i < size; i++){
            if(arr[i].getKey().equals(key)){
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(Object value){
        for(int i = 0; i < size; i++){
            if(arr[i].getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        HashMap map = new HashMap();
        map.put("123", 876);
        map.put("date", new Date());
        map.put("end", "end");

        map.put("date", "2019/7/29");
        map.remove("end");
        System.out.println(map.containsKey("123"));
        System.out.println(map.containsValue(876));
        System.out.println(map.get("date"));
        System.out.println(map.size);
    }

}
