package entity;



import java.util.Date;

public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long prodectID;

    public ProductImg() {
    }

    public ProductImg(Long productImgId, String imgAddr, String imgDesc, Integer priority, Date createTime, Long prodectID) {
        this.productImgId = productImgId;
        this.imgAddr = imgAddr;
        this.imgDesc = imgDesc;
        this.priority = priority;
        this.createTime = createTime;
        this.prodectID = prodectID;
    }

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
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

    public Long getProdectID() {
        return prodectID;
    }

    public void setProdectID(Long prodectID) {
        this.prodectID = prodectID;
    }
}
