package com.wd.tech.page.informationpage.bean;

import java.util.List;

public class NewsAdvisoryBean {

    /**
     * result : [{"collection":1,"id":55,"releaseTime":1539587804000,"share":0,"source":"南七道","summary":"这两年在大数据领域，纯粹讲概念没有技术的公司都死完了。","thumbnail":"https://img.huxiucdn.com/article/cover/201510/13/174903890379.png?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/png","title":"谁杀死了大数据创业者？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":0,"infoAdvertisingVo":{"content":"八维教育","id":1,"pic":"http://172.17.8.100/images/tech/ad/bw.png","url":"http://www.bwie.com"},"share":0,"whetherAdvertising":1,"whetherCollection":0,"whetherPay":0},{"collection":0,"id":53,"releaseTime":1539585103000,"share":0,"source":"高街高参","summary":"两天前，马云现身杭州云栖小镇，这次他的露面不是因为阿里巴巴集团的活动，也不是业界峰会，而是为政府站台，参加杭州市为打造全国数字经济第一城举办的动员大会。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/14/123637310014.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"马云力挺杭州\u201c数字经济\u201d第一城之后，我先咽下这口泡沫","whetherAdvertising":2,"whetherCollection":2,"whetherPay":1},{"collection":0,"id":52,"releaseTime":1539584990000,"share":0,"source":"Eastland","summary":"海底捞以17.8港元在港发售4.2亿新股，募集资金净额72.7亿港元，高瓴、景林、雪湖等基石投资者认购数占IPO发行量的38.95%。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/15/073628459039.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"风雨飘摇中，海底捞能否成为投资避风港？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":51,"releaseTime":1539584821000,"share":0,"source":"零售威观察©","summary":"作为一家英国零售商，Primark主要销售服装、配饰、美妆、箱包和家居用品。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/15/081855659893.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"不玩电商的Primark是如何横扫美国零售市场的？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":50,"releaseTime":1539582903000,"share":0,"source":"全天候科技","summary":"区块链行业人才薪酬的下滑是理性回归的信号，但这个行业的人才需求缺口依然巨大。","thumbnail":"https://img.huxiucdn.com/article/cover/201611/30/184900020993.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"行业薪酬\u201c大跳水\u201d，区块链真的凉了？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":1},{"collection":0,"id":49,"releaseTime":1539582707000,"share":0,"source":"蓝狐笔记","summary":"围绕区块链的炒作很多，你会听到一系列的夸大宣传","thumbnail":"https://img.huxiucdn.com/article/cover/201804/13/084211153166.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"为什么说区块链没那么简单？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":48,"releaseTime":1539582496000,"share":0,"source":"网事风云","summary":"现在区块链依然很火，但对于不炒币的大多数人来说，区块链依然有点虚无缥缈，盛名之下其实难副，没什么拿得出手的应用。","thumbnail":"https://img.huxiucdn.com/article/cover/201802/02/072817315981.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"区块链落地实体经济，这个领域可能是先锋","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":47,"releaseTime":1539582250000,"share":0,"source":"蓝狐笔记","summary":"区块链本身是算法的信任或是分布式的信任，通过去中心化的方式达成了一个系统信任。区块链的核心就是达成了无需传统中介方的信任，实现了价值的自由流通。","thumbnail":"https://img.huxiucdn.com/article/cover/201808/21/145038872529.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"为什么说区块链\u201c无需信任\u201d？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":46,"releaseTime":1539582121000,"share":0,"source":"懂懂笔记","summary":"自从币圈爆出了\u201c录音门\u201d丑闻之后，链圈也受到了波及，这使得网上关于区块链的话题，一时间似乎少了很多。","thumbnail":"https://img.huxiucdn.com/article/cover/201712/11/142821236699.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"\u201c大会\u201d要开、\u201c大屏\u201d要占：区块链\u201c药\u201d不能停","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * collection : 1
         * id : 55
         * releaseTime : 1539587804000
         * share : 0
         * source : 南七道
         * summary : 这两年在大数据领域，纯粹讲概念没有技术的公司都死完了。
         * thumbnail : https://img.huxiucdn.com/article/cover/201510/13/174903890379.png?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/png
         * title : 谁杀死了大数据创业者？
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         * infoAdvertisingVo : {"content":"八维教育","id":1,"pic":"http://172.17.8.100/images/tech/ad/bw.png","url":"http://www.bwie.com"}
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;
        private InfoAdvertisingVoBean infoAdvertisingVo;

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }

        public InfoAdvertisingVoBean getInfoAdvertisingVo() {
            return infoAdvertisingVo;
        }

        public void setInfoAdvertisingVo(InfoAdvertisingVoBean infoAdvertisingVo) {
            this.infoAdvertisingVo = infoAdvertisingVo;
        }

        public static class InfoAdvertisingVoBean {
            /**
             * content : 八维教育
             * id : 1
             * pic : http://172.17.8.100/images/tech/ad/bw.png
             * url : http://www.bwie.com
             */

            private String content;
            private int id;
            private String pic;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
