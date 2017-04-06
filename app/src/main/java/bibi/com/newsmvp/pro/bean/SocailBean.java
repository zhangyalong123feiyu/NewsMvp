package bibi.com.newsmvp.pro.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-1-6.
 */
public class SocailBean implements Serializable {
    private int code;
    private String msg;
    private List<SocailInfo> newslist;

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SocailInfo> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<SocailInfo> newslist) {
        this.newslist = newslist;
    }

    public class SocailInfo {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
