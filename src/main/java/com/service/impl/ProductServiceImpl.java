package com.service.impl;

import com.dao.ProductDao;
import com.dao.ProductImgDao;
import com.dto.ImageHolder;
import com.dto.ProductExecution;
import com.entity.Product;
import com.entity.ProductImg;
import com.enums.ProductStateEnum;
import com.exceptions.ProductOperationException;
import com.service.ProductService;
import com.util.ImageUtil;
import com.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
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
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder>  productImgHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum < 0) {
                    throw new ProductOperationException("创建失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建失败" + e.toString());
            }
            if (productImgHolderList != null && productImgHolderList.size() > 0) {
                addProductImgList(product, productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder>  productImgHolderList)throws ProductOperationException {
       if (product!=null && product.getShop()!=null && product.getShop().getShopId()!=null && product.getProductId()!=null){
            product.setLastEditTime(new Date());
            Product tempProduct=productDao.queryProductById(product.getProductId());
            if (thumbnail==null){
               if (tempProduct.getImgAddr()!=null){
                   ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
               }
               addThumbnail(product,thumbnail);
            }
            if (productImgHolderList!=null && productImgHolderList.size()>0){
                  deleteProductImgList(product.getProductId());
            }
               addProductImgList(product,productImgHolderList);

            try {
                int effectNum=productDao.updateProduct(product);
                if (effectNum<0){
                    throw new ProductOperationException("更新失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS);

            }catch (ProductOperationException e){
                throw new ProductOperationException("更新失败"+e.toString());
            }
       }else {
           return new ProductExecution(ProductStateEnum.EMPTY);
       }

    }








    @Override
    public List<Product> getProductList(Product productCodition, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.queryProductById(productId);
    }


    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setCreateTime(new Date());
            productImg.setImgAddr(imgAddr);
            productImg.setproductId(product.getProductId());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum < 0) {
                    throw new ProductOperationException("详情图片错误");
                }
            } catch (Exception e) {
                throw new ProductOperationException("详情图片错误" + e.toString());
            }
        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String desc = PathUtil.getShopImgPath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, desc);
        product.setImgAddr(thumbnailAddr);
    }

    private void deleteProductImgList(Long productId){
        List<ProductImg> productImgList=productImgDao.queryProductImgList(productId);
        for (ProductImg pd:productImgList) {
            ImageUtil.deleteFileOrPath(pd.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);

    }
}
