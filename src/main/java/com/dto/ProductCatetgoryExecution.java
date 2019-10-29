package com.dto;

import com.entity.ProductCategory;
import com.entity.ShopCategory;
import com.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCatetgoryExecution {
    private int state;
    private String stateInfo;
    private List<ProductCategory> productCategoryLis;

    public ProductCatetgoryExecution(ProductCategoryStateEnum ps) {
        this.state = ps.getState();
        this.stateInfo = ps.getStateInfo();
    }

    public ProductCatetgoryExecution(ProductCategoryStateEnum ps, List<ProductCategory> productCategoryLis) {
        this.state = ps.getState();
        this.stateInfo = ps.getStateInfo();
        this.productCategoryLis = productCategoryLis;
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

    public List<ProductCategory> getProductCategoryLis() {
        return productCategoryLis;
    }

    public void setProductCategoryLis(List<ProductCategory> productCategoryLis) {
        this.productCategoryLis = productCategoryLis;
    }
}
