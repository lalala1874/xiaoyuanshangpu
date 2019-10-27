package com.service.impl;

import com.dao.ShopCategoryDao;
import com.entity.ShopCategory;
import com.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShopCategoryServiceImol implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {



        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
