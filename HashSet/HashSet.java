package com.mengfansheng.collection;

import java.util.HashMap;

public class HashSet {
    private static final Object PRESENT = new Object();
    HashMap map;

    public HashSet(){
        map = new HashMap();
    }

    public void add(Object o){ // set的不可重复利用的是map的key不可重复的特性
        map.put(o, PRESENT);
    }
    public int size(){
        return map.size();
    }

    public void remove(Object o){
        map.remove(o);
    }
}
