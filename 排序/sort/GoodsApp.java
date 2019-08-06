package com.mengfansheng.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoodsApp {

    public  static void main(String[] args){
        List<Goods> list = new ArrayList<Goods>();
        list.add(new Goods(20, "java", 30));
        list.add(new Goods(30, "oracle", 50));
        list.add(new Goods(42, "python", 40));
        list.add(new Goods(23, "rust", 20));

        Collections.sort(list, new GoodsPriceSort());

        System.out.println(list);
    }
}
