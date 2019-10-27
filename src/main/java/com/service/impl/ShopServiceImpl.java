package com.service.impl;

import com.dao.ShopDao;
import com.dto.ShopExecution;
import com.entity.Shop;
import com.enums.ShopStateEnum;
import com.exceptions.ShopOperationExpection;
import com.service.ShopService;
import com.util.ImageUtil;
import com.util.PageCalculator;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
                        addShopImg(shop, shopImgInputStream, fileName);

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


    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationExpection {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                if (shopImgInputStream != null && fileName != null && "".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);

                }
                shop.setLasgEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ShopOperationExpection("modifyShop error:" + e.getMessage());
            }

        }


    }

    /**
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.CalculatorRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (null != shopList) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNNER_ERROR.getState());
        }
        return se;

    }


    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        //设置shop的ShopImg属性
        shop.setShopImg(shopImgAddr);

    }
}
