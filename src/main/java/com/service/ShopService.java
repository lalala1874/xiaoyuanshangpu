package com.service;

import com.dto.ShopExecution;
import com.entity.Shop;
import com.exceptions.ShopOperationExpection;


import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream ShopImgInputStream,String fileName) throws ShopOperationExpection;
    Shop getByShopId(Long shopId);
    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationExpection;
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
