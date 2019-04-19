package com.wd.tech.page.informationpage.bean;

import java.util.List;

public class NewsAdvisorySearchBean {

    /**
     * result : [{"id":50,"releaseTime":1539582903000,"source":"全天候科技","title":"行业薪酬\u201c大跳水\u201d，区块链真的凉了？"},{"id":49,"releaseTime":1539582707000,"source":"蓝狐笔记","title":"为什么说区块链没那么简单？"},{"id":48,"releaseTime":1539582496000,"source":"网事风云","title":"区块链落地实体经济，这个领域可能是先锋"},{"id":47,"releaseTime":1539582250000,"source":"蓝狐笔记","title":"为什么说区块链\u201c无需信任\u201d？"},{"id":46,"releaseTime":1539582121000,"source":"懂懂笔记","title":"\u201c大会\u201d要开、\u201c大屏\u201d要占：区块链\u201c药\u201d不能停"},{"id":45,"releaseTime":1539574363000,"source":"IT桔子","title":"区块链媒体半年融资超11亿，昨晚被封了好几家"},{"id":44,"releaseTime":1539574191000,"source":"硬蛋©","title":"快播正式破产，王欣跑去搞区块链和AI了"},{"id":43,"releaseTime":1539573670000,"source":"区块律动","title":"蹭了区块链的港股美股上市公司们，真的涨疯了？"},{"id":42,"releaseTime":1539572725000,"source":"人民创投","title":"区块链+版权：创作场从此\u201c天下无贼\u201d？"},{"id":41,"releaseTime":1539569743000,"source":"区块律动","title":"迅雷恐怕要错过\u201c区块链\u201d这波红利了"},{"id":29,"releaseTime":1539329967000,"source":"量子位","title":"比特大陆是至尊宝，区块链是白晶晶，AI是他的紫霞仙子"}]
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
         * id : 50
         * releaseTime : 1539582903000
         * source : 全天候科技
         * title : 行业薪酬“大跳水”，区块链真的凉了？
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
