package com.tsdl.practices.entity;

import com.tsdl.practices.entity.Type;

import java.util.List;

public class TypeListResult {

    /**
     * msg : success
     * code : 0
     * page : {"totalCount":2,"pageSize":10,"totalPage":1,"currPage":1,"list":[{"id":4,"name":"锅包肉","remark":"<p><strong>主料<\/strong>：猪里脊肉、土豆淀粉、豆油<\/p><p><strong>配料<\/strong>：胡萝卜丝、葱丝、姜丝、香菜段<\/p><p><strong>料汁<\/strong>：白糖、白醋、酱油、食盐<\/p><p><strong>制作过程<\/strong>：<\/p><p>1、将猪里脊肉切成筷子粗细的肉片；<\/p><p>2、使用菜刀拍打肉片，使肉质疏松；<\/p><p>3、用清水洗净肉片后攥干水分；<\/p><p>4、加入等量的土豆淀粉抓拌均匀；<\/p><p>5、分次少量加入清水抓拌均匀至淀粉微微成液态；<\/p><p>6、加入少许豆油抓拌均匀；<\/p><p>7、油温6成热下肉片炸制定型捞出；<\/p><p>8、油温升高放入肉片复炸15秒捞出；<\/p><p>9、锅留少许油放入炸好的肉片翻炒；<\/p><p>10、加入配料，大火烹入料汁翻炒10秒出锅；<\/p>","type":6,"typeName":"热菜","materialsRequired":"1,11,2001,24,28,30,9","materialsSelected":"1004,25,26,27","labels":"1","difficulty":5,"videoPath":"test.mp4","imagePath":"guobaorou.jpg"},{"id":7,"name":"111","remark":"<p>111<\/p>","type":4,"typeName":"主食","materialsRequired":"1","materialsSelected":"","labels":"","difficulty":1,"videoPath":null,"imagePath":null}]}
     */

    private String msg;
    private int code;
    private PageBean page;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * totalCount : 2
         * pageSize : 10
         * totalPage : 1
         * currPage : 1
         * list : [{"id":4,"name":"锅包肉","remark":"<p><strong>主料<\/strong>：猪里脊肉、土豆淀粉、豆油<\/p><p><strong>配料<\/strong>：胡萝卜丝、葱丝、姜丝、香菜段<\/p><p><strong>料汁<\/strong>：白糖、白醋、酱油、食盐<\/p><p><strong>制作过程<\/strong>：<\/p><p>1、将猪里脊肉切成筷子粗细的肉片；<\/p><p>2、使用菜刀拍打肉片，使肉质疏松；<\/p><p>3、用清水洗净肉片后攥干水分；<\/p><p>4、加入等量的土豆淀粉抓拌均匀；<\/p><p>5、分次少量加入清水抓拌均匀至淀粉微微成液态；<\/p><p>6、加入少许豆油抓拌均匀；<\/p><p>7、油温6成热下肉片炸制定型捞出；<\/p><p>8、油温升高放入肉片复炸15秒捞出；<\/p><p>9、锅留少许油放入炸好的肉片翻炒；<\/p><p>10、加入配料，大火烹入料汁翻炒10秒出锅；<\/p>","type":6,"typeName":"热菜","materialsRequired":"1,11,2001,24,28,30,9","materialsSelected":"1004,25,26,27","labels":"1","difficulty":5,"videoPath":"test.mp4","imagePath":"guobaorou.jpg"},{"id":7,"name":"111","remark":"<p>111<\/p>","type":4,"typeName":"主食","materialsRequired":"1","materialsSelected":"","labels":"","difficulty":1,"videoPath":null,"imagePath":null}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<Type> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<Type> getList() {
            return list;
        }

        public void setList(List<Type> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "PageBean{" + "totalCount=" + totalCount + ", pageSize=" + pageSize + ", totalPage=" + totalPage +
                    ", currPage=" + currPage + ", list=" + list + '}';
        }
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public boolean isTokenValid() {
        return !(msg != null && msg.contains("token失效"));
    }

    @Override
    public String toString() {
        return "DishListResult{" + "msg='" + msg + '\'' + ", code=" + code + ", page=" + page + '}';
    }
}
