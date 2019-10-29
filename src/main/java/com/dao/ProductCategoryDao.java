package com.dao;

import com.dto.ProductCatetgoryExecution;
import com.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> queryProductCategoryList(long shopId);
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
    int deleteProductCategory(@Param("productCategoryId")Long productCategoryId,@Param("shopId")Long shopId);
}
