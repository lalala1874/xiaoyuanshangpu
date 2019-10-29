package com.service.impl;

import com.dao.ProductCategoryDao;
import com.dto.ProductCatetgoryExecution;
import com.entity.ProductCategory;
import com.enums.ProductCategoryStateEnum;
import com.exceptions.ProductCategoryOperationException;
import com.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;


    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCatetgoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effctedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effctedNum < 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCatetgoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchInsertProductCategory errorL" + e.getMessage());
            }

        } else {
            return new ProductCatetgoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }


    }

    @Override
    @Transactional
    public ProductCatetgoryExecution delectProductCategory(Long productCategoryId, Long shopId) throws ProductCategoryOperationException {

            try {
                int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
                if (effectedNum <=0) {
                    throw new ProductCategoryOperationException("删除失败");
                } else {
                    return new ProductCatetgoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new ProductCategoryOperationException("delectProductCategory error" + e.getMessage());

            }

        }



}