package com.service;

import com.dto.ImageHolder;
import com.dto.ShopExecution;
import com.entity.Shop;
import com.exceptions.ShopOperationExpection;


import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationExpection;
    Shop getByShopId(Long shopId);
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationExpection;
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
