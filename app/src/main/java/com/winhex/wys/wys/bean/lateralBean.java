package com.winhex.wys.wys.bean;

public class lateralBean {

    /**
     * image : http://106.12.199.218:8080/upload/1552576871491.jpg
     * content : 你好啊小姐
     */

    private String image;
    private String content;
    private int Head_portrait;
    public int getHead_portrait() {
        return Head_portrait;
    }

    public void setHead_portrait(int head_portrait) {
        Head_portrait = head_portrait;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
