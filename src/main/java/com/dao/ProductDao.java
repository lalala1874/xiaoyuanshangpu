package com.dao;

import com.entity.Product;

public interface ProductDao {
   int    insertProduct(Product product);
   int    updateProduct(Product product);
   Product queryProductById(Long productId);


}
