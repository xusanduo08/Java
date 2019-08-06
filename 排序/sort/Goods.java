package com.mengfansheng.sort;

/**
 * 实体类
 */
public class Goods {
    private int fav;
    private String name;
    private int price;

    public Goods(int fav, String name, int price) {
        this.fav = fav;
        this.name = name;
        this.price = price;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return "商品名"+this.name+",收藏量"+this.getFav()+",价格"+this.getPrice();
    }
}
