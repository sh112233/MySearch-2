package com.jwei.mysearch.item;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Goods {

    public String Goods_name;
    public String Store_name;
    public String Price;
    public String Distance;
    public int Goods_imageid;

    public Goods(){}

    public Goods(String Goods_name,String Store_name,String Price,int Goods_imageid){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Price = Price;
        this.Goods_imageid = Goods_imageid;
    }

    public Goods(String Goods_name,String Store_name,String Distance,String Price,int Goods_imageid){
        this.Goods_name = Goods_name;
        this.Store_name = Store_name;
        this.Distance = Distance;
        this.Price = Price;
        this.Goods_imageid = Goods_imageid;
    }


    public String Goods_name() {
        return Goods_name;
    }
    public void setGoods_name(String Goods_name) {
        this.Goods_name = Goods_name;
    }

    public String Store_name() {
        return Store_name;
    }
    public void setStore_name(String Store_name) {
        this.Store_name = Store_name;
    }

    public String Price() {
        return Price;
    }
    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String Distance() {
        return Distance;
    }
    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public int Goods_imageid() {
        return Goods_imageid;
    }
    public void setGoods_imageid(int Goods_imageid) {
        this.Goods_imageid = Goods_imageid;
    }
}
