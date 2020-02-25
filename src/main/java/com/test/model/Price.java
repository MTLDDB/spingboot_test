package com.test.model;


public class Price {

  private String objectid;
  private String priceJson;
  private String infodetail_id;
  private java.sql.Timestamp creattime;
  private String detailId;

  private String price_json;
  private String createtime;
  public String getInfodetail_id() {
    return infodetail_id;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public void setInfodetail_id(String infodetail_id) {
    this.infodetail_id = infodetail_id;
  }

  public String getPrice_json() {
    return price_json;
  }

  public void setPrice_json(String price_json) {
    this.price_json = price_json;
  }

  public String getObjectid() {
    return objectid;
  }

  public void setObjectid(String objectid) {
    this.objectid = objectid;
  }


  public String getPriceJson() {
    return priceJson;
  }

  public void setPriceJson(String priceJson) {
    this.priceJson = priceJson;
  }


  public java.sql.Timestamp getCreattime() {
    return creattime;
  }

  public void setCreattime(java.sql.Timestamp creattime) {
    this.creattime = creattime;
  }


  public String getDetailId() {
    return detailId;
  }

  public void setDetailId(String detailId) {
    this.detailId = detailId;
  }

}
