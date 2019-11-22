package com.cysion.media.entity;

public class NewsInfoEntity {

    /**
     * path : https://news.163.com/19/1122/09/EUJ0H43H0001899O.html
     * image : http://cms-bucket.ws.126.net/2019/11/22/82e06f5f34b546879b7a00f157f5540b.png?imageView&thumbnail=140y88&quality=85
     * title : 2019年中科院增选64名院士 年龄最小42岁
     * passtime : 2019-11-22 10:00:35
     */

    private String path;
    private String image;
    private String title;
    private String passtime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }
}
