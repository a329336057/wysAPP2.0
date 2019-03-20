package com.winhex.wys.wys.bean;

import java.util.List;

public class Homebean {

    /**
     * Code : 200
     * meassage : 数据返回成功
     * homerow : [{"image":"http://106.12.199.218:8080/upload/1552576871491.jpg","content":"你好啊小姐"},{"image":"http://106.12.199.218:8080/upload/1552576871491.jpg","content":"你好啊小姐"},{"image":"http://106.12.199.218:8080/upload/1552576871491.jpg","content":"你好啊小姐"},{"image":"http://106.12.199.218:8080/upload/1552576871491.jpg","content":"你好啊小姐"},{"image":"http://106.12.199.218:8080/upload/1552576871491.jpg","content":"你好啊小姐"},{"image":"http://106.12.199.218:8080/upload/BD80CF99560474BBD170AA09677F7FA3.gif","content":"你好啊小姐"}]
     */

    private int Code;
    private String meassage;
    private List<HomerowBean> homerow;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMeassage() {
        return meassage;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public List<HomerowBean> getHomerow() {
        return homerow;
    }

    public void setHomerow(List<HomerowBean> homerow) {
        this.homerow = homerow;
    }

    public static class HomerowBean {
        /**
         * image : http://106.12.199.218:8080/upload/1552576871491.jpg
         * content : 你好啊小姐
         */

        private String image;
        private String content;

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
}
