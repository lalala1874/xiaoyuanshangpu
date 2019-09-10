package entity;

import java.util.Date;
import java.util.List;

public class Product {
    private  Long productId; //
    private  String productName;//
    private  String prodectDesc;//
    private  String imgAddr;//
    private  String normalPrice;//
    private  String promotionPrice;//
    private  Integer priority;//
    private  Date createTime;//
    private  Date lastEditTime;//
    private  Integer enabelStatus;//
    private List<ProductImg> productImgList;//
    private ProductCategory productCategory;//
    private  Shop shop;//

    public Product() {
    }

    public Product(Long productId, String productName, String prodectDesc, String imgAddr, String normalPrice, String promotionPrice, Integer priority, Date createTime, Date lastEditTime, Integer enabelStatus, List<ProductImg> productImgList, ProductCategory productCategory, Shop shop) {
        this.productId = productId;
        this.productName = productName;
        this.prodectDesc = prodectDesc;
        this.imgAddr = imgAddr;
        this.normalPrice = normalPrice;
        this.promotionPrice = promotionPrice;
        this.priority = priority;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.enabelStatus = enabelStatus;
        this.productImgList = productImgList;
        this.productCategory = productCategory;
        this.shop = shop;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdectDesc() {
        return prodectDesc;
    }

    public void setProdectDesc(String prodectDesc) {
        this.prodectDesc = prodectDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnabelStatus() {
        return enabelStatus;
    }

    public void setEnabelStatus(Integer enabelStatus) {
        this.enabelStatus = enabelStatus;
    }

    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
