package com.service;

import com.dto.ProductCatetgoryExecution;
import com.entity.ProductCategory;
import com.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(long shopId);
    ProductCatetgoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException;
    ProductCatetgoryExecution delectProductCategory(Long productCategoryId,Long shopId)throws ProductCategoryOperationException;
}
