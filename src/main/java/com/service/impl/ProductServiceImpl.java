package com.service.impl;

import com.dao.ProductDao;
import com.dao.ProductImgDao;
import com.dto.ImageHolder;
import com.dto.ProductExecution;
import com.entity.Product;
import com.exceptions.ProductOperationException;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList) throws ProductOperationException {
       if (product!=null && product.getShop()!=null && product.getShop().getShopId()!=null){
           product.setCreateTime(new Date());
           product.setLastEditTime(new Date());
           product.setEnabelStatus(1);

       }


        return null;
    }
}
