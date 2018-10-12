package kr.or.visitkorea.model;

import java.util.UUID;

public class Image {
    private String imgId;
    private String cotId;
    private String url;
    private String desc;
    private int isThumb;

    public Image(String cotId, String url, String desc) {
        this.imgId = UUID.randomUUID().toString();
        this.cotId = cotId;
        this.url = url;
        this.desc = desc;
        this.isThumb = url.contains("image3_1") ? 1 : 0;
    }

    public String getImgId() {
        return imgId;
    }

    public String getCotId() {
        return cotId;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public int getIsThumb() {
        return isThumb;
    }
}