package com.dao;

import com.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    int batchInsertProductImg(List<ProductImg> productImgList);
}
