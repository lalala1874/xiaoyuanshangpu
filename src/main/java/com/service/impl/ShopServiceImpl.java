package com.service.impl;

import com.dao.ShopDao;
import com.dto.ShopExecution;
import com.entity.Shop;
import com.enums.ShopStateEnum;
import com.exceptions.ShopOperationExpection;
import com.service.ShopService;
import com.util.ImageUtil;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {

        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setCreateTime(new Date());
            shop.setEnableStatus(0);
            shop.setLasgEditTime(new Date());



            int effectedNum = shopDao.insertShop(shop);



            if (effectedNum <= 0) {
                throw new ShopOperationExpection("店铺创建失败");
            } else {
                if (shopImgInputStream != null) {
                    try {
                        addShopImg(shop,shopImgInputStream,fileName);

                    } catch (Exception e) {
                        throw new ShopOperationExpection("addShopImg error 店铺创建失败" + e.getMessage());
                    }

                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationExpection("更新图片地址失败");
                    }

                }
            }

        } catch (Exception e) {
            throw new ShopOperationExpection("addShop error:" + e.getMessage());
        }


        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
        //设置shop的ShopImg属性
        shop.setShopImg(shopImgAddr);

    }
}
