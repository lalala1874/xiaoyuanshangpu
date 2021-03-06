package com.dto;

import com.entity.Product;
import com.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    private int state;
    private String stateInfo;
    private int count;
    private Product product;
    private List<Product> productList;

    public ProductExecution() {
    }
    public  ProductExecution(ProductStateEnum productStateEnum,Product product){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();
        this.product=product;
    }
    public  ProductExecution(ProductStateEnum productStateEnum){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();

    }
    public ProductExecution(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public ProductExecution(int state, String stateInfo, Product product) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.product = product;
    }

    public ProductExecution(int state, Product product) {
        this.state = state;
        this.product = product;
    }

    public ProductExecution(String stateInfo, Product product) {
        this.stateInfo = stateInfo;
        this.product = product;
    }

    public ProductExecution(int state, String stateInfo, List<Product> productList) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.productList = productList;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
