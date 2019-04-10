package com.winhex.wys.wys.bean;

import java.util.List;

public class Homebean {


    /**
     * Code : 200
     * meassage : 数据返回成功
     * homerow : [{"image":"http://106.12.199.218:8080/upload/1554797568622438.jpeg,","content":"啊啦"},{"image":"http://106.12.199.218:8080/upload/1554536167158536.jpeg,","content":"去骑车耍"},{"image":"http://106.12.199.218:8080/upload/1554266463769292.png,","content":"事情"},{"image":"http://106.12.199.218:8080/upload/155426632795862.jpeg,","content":"吃完了"},{"image":"http://106.12.199.218:8080/upload/1554136682810596.jpeg,http://106.12.199.218:8080/upload/1554136683165904.jpeg,http://106.12.199.218:8080/upload/1554136683462128.jpeg,http://106.12.199.218:8080/upload/1554136683773219.jpeg,","content":"好吃"},{"image":"http://106.12.199.218:8080/upload/1554093058691647.jpeg,","content":"吃完饭抽烟"},{"image":"http://106.12.199.218:8080/upload/1553939396738564.jpeg,","content":"下班咯"},{"image":"http://106.12.199.218:8080/upload/1553902804783410.jpeg,","content":"雄伟的桥"},{"image":"http://106.12.199.218:8080/upload/1553851854215699.jpeg,","content":"下班咯"},{"image":"http://106.12.199.218:8080/upload/1553679975214516.jpeg,","content":"wzy胖子"},{"image":"http://106.12.199.218:8080/upload/1553612828314110.jpeg,","content":"非得躺在外面的床睡觉"},{"image":"http://106.12.199.218:8080/upload/155359459369832.jpeg,","content":"没人坐得哈哈"},{"image":"http://106.12.199.218:8080/upload/155359354612369.jpeg,","content":"三个父母 叽叽喳喳的"},{"image":"http://106.12.199.218:8080/upload/1553524490716742.jpeg,","content":"洗脚呢"},{"image":"http://106.12.199.218:8080/upload/1553502392613513.jpeg,http://106.12.199.218:8080/upload/1553502392729742.jpeg,http://106.12.199.218:8080/upload/1553502392888539.png,","content":"你好呀"},{"image":"http://106.12.199.218:8080/upload/1553502392613513.jpeg,http://106.12.199.218:8080/upload/1553502392729742.jpeg,http://106.12.199.218:8080/upload/1553502392888539.png,","content":"你好呀"},{"image":"http://106.12.199.218:8080/upload/1553492638831356.gif,http://106.12.199.218:8080/upload/img-bc168cb6c0f629ccb4c32c4fca099263.jpg,","content":"你好"},{"image":"http://106.12.199.218:8080/upload/1553258545556273.jpeg,","content":"023"},{"image":"http://106.12.199.218:8080/upload/1553246016135173.jpeg,","content":"下班啦 哈哈"},{"image":"http://106.12.199.218:8080/upload/155324132831989.jpeg,","content":"wqeqeqewqewqewqe"},{"image":"http://106.12.199.218:8080/upload/1553241081113490.jpeg,","content":"瓶子 装着幸福"},{"image":"http://106.12.199.218:8080/upload/1553240998270526.jpeg,","content":""},{"image":"http://106.12.199.218:8080/upload/1553223633399983.jpeg,","content":""},{"image":"http://106.12.199.218:8080/upload/1553218109579384.jpeg,","content":" KKK图"},{"image":"http://106.12.199.218:8080/upload/1553186441434336.png,","content":""},{"image":"http://106.12.199.218:8080/upload/1553186363967862.png,","content":""},{"image":"http://106.12.199.218:8080/upload/1553186247974224.jpeg,","content":""},{"image":"http://106.12.199.218:8080/upload/1553185381575393.jpeg,","content":""}]
     * articllist : [{"articlename":"啊实打实","mastergraph":"http://192.168.3.121:8080/banner/8399620190409.png","aid":4,"describes":"的傲视","url":"http://192.168.3.121:8080/wys/articl/content?aid=4","calssfiy":"undefined"},{"articlename":"1321321","mastergraph":"http://192.168.3.121:8080/banner/1548120190409.png","aid":3,"describes":"2132","url":"http://192.168.3.121:8080/wys/articl/content?aid=3","calssfiy":"undefined"},{"articlename":"21213","mastergraph":"http://192.168.3.121:8080/banner/4820920190409.png","aid":2,"describes":"撒打算的撒","url":"http://192.168.3.121:8080/wys/articl/content?aid=2","calssfiy":"undefined"},{"articlename":"额我去额请问去 ","mastergraph":"http://106.12.199.218:8080/banner/2664820190409.png","aid":1,"describes":"额请问请问","url":"http://192.168.3.121:8080/wys/articl/content?aid=1","calssfiy":"undefined"}]
     */

    private int Code;
    private String meassage;
    private List<HomerowBean> homerow;
    private List<ArticllistBean> articllist;

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

    public List<ArticllistBean> getArticllist() {
        return articllist;
    }

    public void setArticllist(List<ArticllistBean> articllist) {
        this.articllist = articllist;
    }

    public static class HomerowBean {
        /**
         * image : http://106.12.199.218:8080/upload/1554797568622438.jpeg,
         * content : 啊啦
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

    public static class ArticllistBean {
        /**
         * articlename : 啊实打实
         * mastergraph : http://192.168.3.121:8080/banner/8399620190409.png
         * aid : 4
         * describes : 的傲视
         * url : http://192.168.3.121:8080/wys/articl/content?aid=4
         * calssfiy : undefined
         */

        private String articlename;
        private String mastergraph;
        private int aid;
        private String describes;
        private String url;
        private String calssfiy;

        public String getArticlename() {
            return articlename;
        }

        public void setArticlename(String articlename) {
            this.articlename = articlename;
        }

        public String getMastergraph() {
            return mastergraph;
        }

        public void setMastergraph(String mastergraph) {
            this.mastergraph = mastergraph;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCalssfiy() {
            return calssfiy;
        }

        public void setCalssfiy(String calssfiy) {
            this.calssfiy = calssfiy;
        }
    }
}

