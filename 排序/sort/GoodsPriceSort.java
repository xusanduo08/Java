package com.mengfansheng.sort;

import java.util.Comparator;

/**
 * 排序业务类
 */
public class GoodsPriceSort implements Comparator<Goods> {

    public int compare(Goods o1, Goods o2){
        return o1.getPrice() - o2.getPrice() > 0 ? 1 : o1.getPrice() == o2.getPrice() ? 0 : -1;
    }
}
