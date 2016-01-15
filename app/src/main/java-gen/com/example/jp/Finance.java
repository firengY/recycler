package com.example.jp;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "FINANCE".
 */
public class Finance {

    private Long id;
    private String imagesrc;
    private String digest;
    private String replyCount;
    private Integer hasAD;
    private String skipType;
    private String imgsrc;
    private String title;
    private String url_3w;

    public Finance() {
    }

    public Finance(Long id) {
        this.id = id;
    }

    public Finance(Long id, String imagesrc, String digest, String replyCount, Integer hasAD, String skipType, String imgsrc, String title, String url_3w) {
        this.id = id;
        this.imagesrc = imagesrc;
        this.digest = digest;
        this.replyCount = replyCount;
        this.hasAD = hasAD;
        this.skipType = skipType;
        this.imgsrc = imgsrc;
        this.title = title;
        this.url_3w = url_3w;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getHasAD() {
        return hasAD;
    }

    public void setHasAD(Integer hasAD) {
        this.hasAD = hasAD;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

}
