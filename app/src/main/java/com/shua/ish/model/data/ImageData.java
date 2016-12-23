package com.shua.ish.model.data;

import com.shua.ish.model.data.entity.ImageList;

import java.util.List;

public class ImageData {

    private int showapi_res_code;

    private String showapi_res_error;
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-06 23:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/03/15/201605031559105491-5159032.jpg","title":"甜美摩天轮清纯美女迷人照片[贴图]","url":"http://bbs.voc.com.cn/mm/meinv-7242841-0-1.html"},{"ctime":"2016-05-06 23:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/05/08/201605050848466551-4217076.jpg","title":"[贴图]夏季外拍人像","url":"http://bbs.voc.com.cn/mm/meinv-7245319-0-1.html"},{"ctime":"2016-05-06 22:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/05/14/201605051438214191-1988454.jpg","title":"【壁纸长廊】\u2015\u2015亚洲美女壁纸选（756）【31P】[分享]","url":"http://bbs.voc.com.cn/mm/meinv-7245889-0-1.html"},{"ctime":"2016-05-06 20:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/06/16/201605061646151681-1988454.jpg","title":"【壁纸长廊】\u2015\u2015亚洲美女壁纸选（763）【22P】[分享]","url":"http://bbs.voc.com.cn/mm/meinv-7247998-0-1.html"},{"ctime":"2016-05-06 18:00","description":"华声美女","picUrl":"http://pic.newface.cn/article/2016/05/20160505100923_3433.jpg","title":"赵芷晗时尚大片 展现高贵洒脱率性十足","url":"http://bbs.voc.com.cn/mm/meinv-7248015-0-1.html"},{"ctime":"2016-05-06 16:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/06/15/201605061533223781-5170832.jpg","title":"清雅美女[推荐]","url":"http://bbs.voc.com.cn/mm/meinv-7247795-0-1.html"},{"ctime":"2016-05-06 15:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/06/13/20160506134243431-4365941.jpg","title":"优雅的身姿 匀称的身段","url":"http://bbs.voc.com.cn/mm/meinv-7247519-0-1.html"},{"ctime":"2016-05-06 15:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/05/10/201605051005071731-1988454.jpg","title":"【壁纸长廊】\u2015\u2015亚洲美女壁纸选（750）【31P】[分享]","url":"http://bbs.voc.com.cn/mm/meinv-7245459-0-1.html"},{"ctime":"2016-05-06 15:00","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/12/m.xxxiao.com_970733ac5dbb52f09e1c7ee2d2da5ec8-760x500.jpg","title":"大胸女神刘娅希性感黑丝私拍","url":"http://m.xxxiao.com/15732"},{"ctime":"2016-05-06 13:00","description":"华声美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-05/06/11/20160506115118911-3356886.jpg","title":"过西风十里【转载】","url":"http://bbs.voc.com.cn/mm/meinv-7247381-0-1.html"}]
     */

    private ImageDataBean showapi_res_body;


    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ImageDataBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ImageDataBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ImageDataBean {
        private int code;
        private String msg;
        private List<ImageList> newslist;

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

        public List<ImageList> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<ImageList> newslist) {
            this.newslist = newslist;
        }
    }
}
