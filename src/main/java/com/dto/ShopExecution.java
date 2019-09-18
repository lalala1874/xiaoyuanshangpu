package com.dto;

import com.entity.Shop;
import com.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution
{

    public  ShopExecution(){}

    private int state;

    private String stateInfo;

    private int count;

    private Shop shop;

    private List<Shop> shopList;

    /**
     * 创建失败的构造器
     * @param shopSateEnum
     */
    public ShopExecution(ShopStateEnum shopSateEnum){
    this.state=shopSateEnum.getState();
    this.stateInfo=shopSateEnum.getStateInfo();
}

    /**
     * 成功构造器
     * @param shopSateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopSateEnum,Shop shop){
        this.state=shopSateEnum.getState();
        this.stateInfo=shopSateEnum.getStateInfo();
        this.shop=shop;
    }
    public ShopExecution(ShopStateEnum shopSateEnum,List<Shop> shopList){
        this.state=shopSateEnum.getState();
        this.stateInfo=shopSateEnum.getStateInfo();
        this.shopList=shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}

