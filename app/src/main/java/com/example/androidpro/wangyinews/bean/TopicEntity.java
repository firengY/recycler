package com.example.androidpro.wangyinews.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by firengy
 * on 16-1-11.
 * Email: 18811372352@163.com
 */
public class TopicEntity {


    /**
     * message : 操作成功
     * data : {"expertList":[{"expertId":"EX7819202780533476264","alias":"我是作家苏芩，关于女性职场、生活、情感等问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/taMlQ3c5qre=e9huvMyAcdDh0L2o9rIBtARSR6WI7PBb21452492437583.jpg","name":"苏芩","description":"苏芩，\u201c新女学\u201d思潮发起人，著名情感畅销书作家，著有《做自己的女王》《男人天生被她们吸引》《20岁跟对人，30岁做对事》《世上没有人比你更重要》等作品十余部，销量过百万册，入选中国作家富豪榜。相继担任百余档电视节目点评专家、嘉宾。2012年3月22日发起\u201c闺蜜日\u201d活动，被都市白领誉为\u201c大众闺蜜\u201d","headpicurl":"http://dingyue.nosdn.127.net/8G5QzvaqiSmROTCGOU3sY2ZrDBIDNn3oH1rgIjJZFWVAD1450694302966.jpg","classification":"人文","state":1,"expertState":1,"concernCount":8411,"createTime":1450753135208,"title":"畅销书作家"},{"expertId":"EX8635704607709333696","alias":"我是心理治疗师李昂，你内心的焦虑、恐惧、沮丧和心理瘦身问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/wdUU8YFsG040MdDcwIPNShbempOdNCECEmgKZor2U463d1452502748700.jpg","name":"李昂","description":"积极·跨文化治疗师，Pab Group创始人，国际积极·跨文化治疗协会成员（WAPP），世界心理治疗协会成员（IFP）。现居北京，主要研究方向是心理治疗和心身医学。","headpicurl":"http://dingyue.nosdn.127.net/XzuY81cpkPO=u=wHlslWtRSyp=zVa6TkZGBUgbU6h38H91452499082879.jpg","classification":"健康","state":1,"expertState":1,"concernCount":245,"createTime":1452496919512,"title":"心理治疗师"},{"expertId":"EX8162114874847219063","alias":"我是李德美，由品酒、学酒、酿酒而乐酒，一路走来，有关葡萄酒的问题，你来问我吧！","picurl":"http://dingyue.nosdn.127.net/lAYeupydqySZlqMIEM4iRdIQrxlmayJouXYU279W0mLal1452491018878.jpg","name":"李德美","description":"虽然第一次体验葡萄酒并不喜欢那酸酸涩涩的感觉，后来却误打误撞被秋后加州葡萄园美景所吸引，而在30岁改行赴波尔多学习葡萄酒，归国后教书、酿酒、做顾问，被国际知名专业杂志评为\u201c世界十大葡萄酒顾问\u201d。","headpicurl":"http://dingyue.nosdn.127.net/u0qzAvDs21FbhFFT1pTGOxfDISJAQWJ8UWpIZQC4Hc2vK1452483741067.jpg","classification":"生活","state":1,"expertState":1,"concernCount":310,"createTime":1452491044989,"title":"世界十大葡萄酒顾问"},{"expertId":"EX-433744285902633489","alias":"我是不正经小百科，擅长一本正经的胡说八道，承接任何问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/1Jm3CfzSp=BZf=VGC87iohPbeS7FQPykqctjM=hWYYFHu1450279121487.jpg","name":"一本道","description":"不自由撰稿人，非著名影视评论家，不知名媒体评论家，不精通医、农、工、商的不正经小百科，能够一本正经胡说八道的文字工作者。","headpicurl":"http://dingyue.nosdn.127.net/vMWN9LY2NA69doDi1HojdoiKrtPUbxDhXQ79v6e3=s94R1450278993650.jpg","classification":"人文","state":1,"expertState":1,"concernCount":29422,"createTime":1450279220317,"title":"文字工作者"},{"expertId":"EX4787885156528361341","alias":"我是网易跟贴的小编，关于跟贴的一切问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/7UnMB=9kURe9BgVUZ0AYNRV=3HvpYueXfJ8kc588e6alE1452147192036.jpg","name":"网易跟贴","description":"网易跟贴汇集了大量有才网友。这里有犀利的点评，好玩的段子，滑稽的二楼定律，精彩的对诗接龙，风趣的高级黑，是网民智慧和社会生态的集中反映。","headpicurl":"http://dingyue.nosdn.127.net/XGA3dSDVLNOhL3cPnsHXMDB38O6pAXwnEC5jvB5tlWkAQ1452146906153.jpg","classification":"其他","state":1,"expertState":1,"concernCount":1760,"createTime":1452147215252,"title":"编辑"},{"expertId":"EX02775130046412117148","alias":"我是张大伟，关于孩子择校和国际教育交流的问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/BOr5bDOmK=zIHf0YvLfto6XTR4hkaBCpScxumcGN1ImXw1452244807607.jpg","name":"张大伟","description":"我是张大伟，从事10多年教育工作。涉足职业教育、学历教育、和国际教育交流多个维度。","headpicurl":"http://dingyue.nosdn.127.net/4hwbBYs6thumxVqabAFcWwsNZ2cOmQzuGHE7aVK7WWgHQ1452243736763.jpg","classification":"教育","state":1,"expertState":1,"concernCount":2111,"createTime":1452243829702,"title":"教育从业者"},{"expertId":"EX5651564184543783446","alias":"我是珠宝设计师Kobe，关于珠宝，腕表，奢侈品配件及周边问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/HjQjEcBA05DZBjDYnl2V6uWvG16ssF69ZNyDoNFu83NJl1452495450396.jpg","name":"kobe zhao","description":"赵寅初，独立珠宝及珠宝腕表设计师。毕业于南非斯泰林波什大学珠宝设计专业并获得荣誉学士学位；曾就读于厉峰集团米兰创意设计学院并获得奢侈品产品设计硕士学位。","headpicurl":"http://dingyue.nosdn.127.net/yq=gYSB1DlAm5rcrM60RMA8mp2jWCPRa3UtgmCV=8B5zX1450165817041.jpg","classification":"时尚","state":1,"expertState":1,"concernCount":7205,"createTime":1450252432844,"title":"珠宝及珠宝腕表设计师"},{"expertId":"EX501352181604589361","alias":"我是一个严肃的狗，擅长回答人类提出的各种各样的问题，请大家信任我，问我吧！","picurl":"http://dingyue.nosdn.127.net/z8f0oqMehFjGn1asNFktUa0aRlNF=vZh5dt3GygtPYuVo1449835868692.JPG","name":"哈朗","description":"一个狗。擅长思考、咀嚼沙发、调戏小狗。热爱吃冰淇淋。语言水平接近小学一年级。","headpicurl":"http://dingyue.nosdn.127.net/rQ4L0I43FiZC8rWZQKBSA1VSDDszJw=kgyJtwQeRXVn9Q1451291830457.jpg","classification":"人文","state":1,"expertState":1,"concernCount":13236,"createTime":1449835885156,"title":"思考者"},{"expertId":"EX04341857870657970387","alias":"我是祁延瑾，网易房产小编，关于楼市的问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/6yCKIxJjaIdKj7nLQfhEAzwzWilWCw58ZWCGvz1cHyy8K1452238816545.png","name":"祁延瑾","description":"我是祁延瑾，被打上\u201c逗比\u201d标签的房产小编，入行一年半，虽然买不起房但我依然对它爱的深沉。我尽量以最\u201c专业\u201d的角度回答你所提出的问题，大胆的提问吧，实在回答不上来的我就去求助同事(╯^╰)","headpicurl":"http://dingyue.nosdn.127.net/aLqsamXK4WbvtPfjeqZxJYx6HgJGR=qn8d=8XIBLAwdZ61452235552875.jpg","classification":"房产","state":1,"expertState":1,"concernCount":2266,"createTime":1452236579175,"title":"房产小编"},{"expertId":"EX05073790527842270009","alias":"我是高级催乳师金莹莹，人社部认证高级催乳师，关于母乳喂养、育儿问题，问我吧！","picurl":"http://dingyue.nosdn.127.net/HJCrMQrJb=e8m=j95Qr9GYRs8zJj9EMVXwwHhoc7pZBoM1452066290454.png","name":"金莹莹","description":"人力资源与社会保障部认证高级催乳师、高级育婴师，母婴护理学院 联合创始人、培训讲师。八年报刊杂志职业媒体人，饱受血泪母乳坎坷后，踏上\u201c准医疗级+标准化\u201d的母婴关怀之路。现育有男宝一枚，快乐母乳17个月中。\r\n","headpicurl":"http://dingyue.nosdn.127.net/B8JNQ0NUXzDpnv2oL6tMCOdandK=wAPRtvN=cX7UkWb0n1452065740798.jpg","classification":"生活","state":1,"expertState":1,"concernCount":4531,"createTime":1452066325086,"title":"母乳哺育顾问"}]}
     * code : 1
     */

    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public DataEntity data;
    @SerializedName("code")
    public int code;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public static class DataEntity {
        /**
         * expertId : EX7819202780533476264
         * alias : 我是作家苏芩，关于女性职场、生活、情感等问题，问我吧！
         * picurl : http://dingyue.nosdn.127.net/taMlQ3c5qre=e9huvMyAcdDh0L2o9rIBtARSR6WI7PBb21452492437583.jpg
         * name : 苏芩
         * description : 苏芩，“新女学”思潮发起人，著名情感畅销书作家，著有《做自己的女王》《男人天生被她们吸引》《20岁跟对人，30岁做对事》《世上没有人比你更重要》等作品十余部，销量过百万册，入选中国作家富豪榜。相继担任百余档电视节目点评专家、嘉宾。2012年3月22日发起“闺蜜日”活动，被都市白领誉为“大众闺蜜”
         * headpicurl : http://dingyue.nosdn.127.net/8G5QzvaqiSmROTCGOU3sY2ZrDBIDNn3oH1rgIjJZFWVAD1450694302966.jpg
         * classification : 人文
         * state : 1
         * expertState : 1
         * concernCount : 8411
         * createTime : 1450753135208
         * title : 畅销书作家
         */

        @SerializedName("expertList")
        public List<ExpertListEntity> expertList;

        public void setExpertList(List<ExpertListEntity> expertList) {
            this.expertList = expertList;
        }

        public List<ExpertListEntity> getExpertList() {
            return expertList;
        }

        @Table(name="ExpertListEntity")
        public static class ExpertListEntity extends Model {
            // 这样可以避免重复
            @Column(name = "expertId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
            @SerializedName("expertId")
            public String expertId;
            @Column(name = "alias")
            @SerializedName("alias")
            public String alias;
            @Column(name = "picurl")
            @SerializedName("picurl")
            public String picurl;
            @Column(name = "name")
            @SerializedName("name")
            public String name;
            @Column(name = "description")
            @SerializedName("description")
            public String description;
            @Column(name = "headpicurl")
            @SerializedName("headpicurl")
            public String headpicurl;
            @Column(name = "classification")
            @SerializedName("classification")
            public String classification;
            @Column(name = "state")
            @SerializedName("state")
            public int state;
            @Column(name = "expertState")
            @SerializedName("expertState")
            public int expertState;
            @Column(name = "concernCount")
            @SerializedName("concernCount")
            public int concernCount;
            @Column(name = "createTime")
            @SerializedName("createTime")
            public long createTime;
            @Column(name = "title")
            @SerializedName("title")
            public String title;

            public void setExpertId(String expertId) {
                this.expertId = expertId;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setHeadpicurl(String headpicurl) {
                this.headpicurl = headpicurl;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setExpertState(int expertState) {
                this.expertState = expertState;
            }

            public void setConcernCount(int concernCount) {
                this.concernCount = concernCount;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExpertId() {
                return expertId;
            }

            public String getAlias() {
                return alias;
            }

            public String getPicurl() {
                return picurl;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getHeadpicurl() {
                return headpicurl;
            }

            public String getClassification() {
                return classification;
            }

            public int getState() {
                return state;
            }

            public int getExpertState() {
                return expertState;
            }

            public int getConcernCount() {
                return concernCount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
