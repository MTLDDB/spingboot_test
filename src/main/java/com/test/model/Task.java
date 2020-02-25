package com.test.model;

public class Task {

  private String objectid;
  private String url;
  private String domain;
  private long status;
  private long failnum;
  private long priority;
  private long stockupdate;
  private long filterupdate;
  private String cookie;
  private String requestWay;
  private String params;
  private String referer;
  private String expansion1;
  private String expansion2;
  private String expansion3;
  private long priceupdate;
  private java.sql.Timestamp createtime;

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public String getObjectid() {
    return objectid;
  }

  public void setObjectid(String objectid) {
    this.objectid = objectid;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getFailnum() {
    return failnum;
  }

  public void setFailnum(long failnum) {
    this.failnum = failnum;
  }


  public long getPriority() {
    return priority;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }


  public long getStockupdate() {
    return stockupdate;
  }

  public void setStockupdate(long stockupdate) {
    this.stockupdate = stockupdate;
  }


  public long getFilterupdate() {
    return filterupdate;
  }

  public void setFilterupdate(long filterupdate) {
    this.filterupdate = filterupdate;
  }


  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }


  public String getRequestWay() {
    return requestWay;
  }

  public void setRequestWay(String requestWay) {
    this.requestWay = requestWay;
  }


  public String getParams() {
    return params;
  }

  public void setParams(String params) {
    this.params = params;
  }


  public String getExpansion1() {
    return expansion1;
  }

  public void setExpansion1(String expansion1) {
    this.expansion1 = expansion1;
  }


  public String getExpansion2() {
    return expansion2;
  }

  public void setExpansion2(String expansion2) {
    this.expansion2 = expansion2;
  }


  public String getExpansion3() {
    return expansion3;
  }

  public void setExpansion3(String expansion3) {
    this.expansion3 = expansion3;
  }


  public long getPriceupdate() {
    return priceupdate;
  }

  public void setPriceupdate(long priceupdate) {
    this.priceupdate = priceupdate;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
