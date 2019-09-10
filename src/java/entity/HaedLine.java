package entity;

import java.util.Date;
//头条
public class HaedLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer prority;
    private Integer enableStatus; //0不可用 1可用
    private Date createTime;
    private Date lastEditTime;

    public HaedLine() {
    }

    public HaedLine(Long lineId, String lineName, String lineLink, String lineImg, Integer prority, Integer enableStatus, Date createTime, Date lastEditTime) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.lineLink = lineLink;
        this.lineImg = lineImg;
        this.prority = prority;
        this.enableStatus = enableStatus;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public Integer getPrority() {
        return prority;
    }

    public void setPrority(Integer prority) {
        this.prority = prority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
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
}
