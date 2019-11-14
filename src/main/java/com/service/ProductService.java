package com.service;

import com.dto.ImageHolder;
import com.dto.ProductExecution;
import com.entity.Product;
import com.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;


public interface ProductService {

    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                               List<ImageHolder>  productImgHolderList)
            throws ProductOperationException;
    ProductExecution modifyProduct(Product product,
                                   ImageHolder thumbnail,
                                   List<ImageHolder>  productImgHolderList)throws ProductOperationException;



    List<Product> getProductList(Product productCodition,int pageIndex,int pageSize);

    Product getProductById(Long productId);




}
