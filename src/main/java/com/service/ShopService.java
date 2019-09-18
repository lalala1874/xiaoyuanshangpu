package com.service;

import com.dto.ShopExecution;
import com.entity.Shop;


import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream ShopImgInputStream,String fileName);
}
