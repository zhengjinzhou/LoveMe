package zhou.com.loveme.json;

import java.util.List;

/**
 * Created by zhou on 2017/8/14.
 *
 *  ####五、获取个人信息
 *
 */

public class JsonOwer {


    /**
     * error : -1
     * memInfo : {"age":22,"autograph":"努力改变命运","birthday":"1995-09-23","book_others":"","books":"古龙","city":{"id":24,"name":"莆田市","priority":0,"province":{"id":2,"name":"福建省","priority":2}},"constellation":"摩羯座","film_others":"","films":"喜剧,惊悚","food_others":"","foods":"北京烤鸭,港式早茶","gender":1,"hashCode":-2147483648,"id":1,"label_others":"","labels":"健谈,局气,热血,叛逆,安静","major":{"hashCode":-2147483648,"id":1,"majorName":"计算机学院","priority":0},"miniPhotoPath":"/upload/file/mini/201708/14093216pdy0.jpg","music":"流行,金属,布鲁士","music_others":"","nickname":"奋斗少年","pair_id":0,"photoPath":"/upload/file/201708/14093215eypm.jpg","pictures":[{"miniUrl":"/upload/file/mini/201708/14093216pdy0.jpg","photoUrl":"/upload/file/201708/14093215eypm.jpg","pictureId":29},{"miniUrl":"/upload/file/mini/201708/14093216lqif.jpg","photoUrl":"/upload/file/201708/14093216q9w7.jpg","pictureId":30}],"realname":"何玉棠","sport_others":"","sports":"健身房,拳击,跆拳道,足球","thumbs_up":0,"travel_others":"","travels":"成都","wechat":""}
     * msg : 获取用户信息成功
     */

    private String error;
    private MemInfoBean memInfo;
    private String msg;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public MemInfoBean getMemInfo() {
        return memInfo;
    }

    public void setMemInfo(MemInfoBean memInfo) {
        this.memInfo = memInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class MemInfoBean {
        /**
         * age : 22
         * autograph : 努力改变命运
         * birthday : 1995-09-23
         * book_others :
         * books : 古龙
         * city : {"id":24,"name":"莆田市","priority":0,"province":{"id":2,"name":"福建省","priority":2}}
         * constellation : 摩羯座
         * film_others :
         * films : 喜剧,惊悚
         * food_others :
         * foods : 北京烤鸭,港式早茶
         * gender : 1
         * hashCode : -2147483648
         * id : 1
         * label_others :
         * labels : 健谈,局气,热血,叛逆,安静
         * major : {"hashCode":-2147483648,"id":1,"majorName":"计算机学院","priority":0}
         * miniPhotoPath : /upload/file/mini/201708/14093216pdy0.jpg
         * music : 流行,金属,布鲁士
         * music_others :
         * nickname : 奋斗少年
         * pair_id : 0
         * photoPath : /upload/file/201708/14093215eypm.jpg
         * pictures : [{"miniUrl":"/upload/file/mini/201708/14093216pdy0.jpg","photoUrl":"/upload/file/201708/14093215eypm.jpg","pictureId":29},{"miniUrl":"/upload/file/mini/201708/14093216lqif.jpg","photoUrl":"/upload/file/201708/14093216q9w7.jpg","pictureId":30}]
         * realname : 何玉棠
         * sport_others :
         * sports : 健身房,拳击,跆拳道,足球
         * thumbs_up : 0
         * travel_others :
         * travels : 成都
         * wechat :
         */

        private int age;
        private String autograph;
        private String birthday;
        private String book_others;
        private String books;
        private CityBean city;
        private String constellation;
        private String film_others;
        private String films;
        private String food_others;
        private String foods;
        private int gender;
        private int hashCode;
        private int id;
        private String label_others;
        private String labels;
        private MajorBean major;
        private String miniPhotoPath;
        private String music;
        private String music_others;
        private String nickname;
        private int pair_id;
        private String photoPath;
        private String realname;
        private String sport_others;
        private String sports;
        private int thumbs_up;
        private String travel_others;
        private String travels;
        private String wechat;
        private List<PicturesBean> pictures;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBook_others() {
            return book_others;
        }

        public void setBook_others(String book_others) {
            this.book_others = book_others;
        }

        public String getBooks() {
            return books;
        }

        public void setBooks(String books) {
            this.books = books;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getFilm_others() {
            return film_others;
        }

        public void setFilm_others(String film_others) {
            this.film_others = film_others;
        }

        public String getFilms() {
            return films;
        }

        public void setFilms(String films) {
            this.films = films;
        }

        public String getFood_others() {
            return food_others;
        }

        public void setFood_others(String food_others) {
            this.food_others = food_others;
        }

        public String getFoods() {
            return foods;
        }

        public void setFoods(String foods) {
            this.foods = foods;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel_others() {
            return label_others;
        }

        public void setLabel_others(String label_others) {
            this.label_others = label_others;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public MajorBean getMajor() {
            return major;
        }

        public void setMajor(MajorBean major) {
            this.major = major;
        }

        public String getMiniPhotoPath() {
            return miniPhotoPath;
        }

        public void setMiniPhotoPath(String miniPhotoPath) {
            this.miniPhotoPath = miniPhotoPath;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }

        public String getMusic_others() {
            return music_others;
        }

        public void setMusic_others(String music_others) {
            this.music_others = music_others;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPair_id() {
            return pair_id;
        }

        public void setPair_id(int pair_id) {
            this.pair_id = pair_id;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public void setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getSport_others() {
            return sport_others;
        }

        public void setSport_others(String sport_others) {
            this.sport_others = sport_others;
        }

        public String getSports() {
            return sports;
        }

        public void setSports(String sports) {
            this.sports = sports;
        }

        public int getThumbs_up() {
            return thumbs_up;
        }

        public void setThumbs_up(int thumbs_up) {
            this.thumbs_up = thumbs_up;
        }

        public String getTravel_others() {
            return travel_others;
        }

        public void setTravel_others(String travel_others) {
            this.travel_others = travel_others;
        }

        public String getTravels() {
            return travels;
        }

        public void setTravels(String travels) {
            this.travels = travels;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public List<PicturesBean> getPictures() {
            return pictures;
        }

        public void setPictures(List<PicturesBean> pictures) {
            this.pictures = pictures;
        }

        public static class CityBean {
            /**
             * id : 24
             * name : 莆田市
             * priority : 0
             * province : {"id":2,"name":"福建省","priority":2}
             */

            private int id;
            private String name;
            private int priority;
            private ProvinceBean province;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public ProvinceBean getProvince() {
                return province;
            }

            public void setProvince(ProvinceBean province) {
                this.province = province;
            }

            public static class ProvinceBean {
                /**
                 * id : 2
                 * name : 福建省
                 * priority : 2
                 */

                private int id;
                private String name;
                private int priority;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPriority() {
                    return priority;
                }

                public void setPriority(int priority) {
                    this.priority = priority;
                }
            }
        }

        public static class MajorBean {
            /**
             * hashCode : -2147483648
             * id : 1
             * majorName : 计算机学院
             * priority : 0
             */

            private int hashCode;
            private int id;
            private String majorName;
            private int priority;

            public int getHashCode() {
                return hashCode;
            }

            public void setHashCode(int hashCode) {
                this.hashCode = hashCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMajorName() {
                return majorName;
            }

            public void setMajorName(String majorName) {
                this.majorName = majorName;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }
        }

        public static class PicturesBean {
            /**
             * miniUrl : /upload/file/mini/201708/14093216pdy0.jpg
             * photoUrl : /upload/file/201708/14093215eypm.jpg
             * pictureId : 29
             */

            private String miniUrl;
            private String photoUrl;
            private int pictureId;

            public String getMiniUrl() {
                return miniUrl;
            }

            public void setMiniUrl(String miniUrl) {
                this.miniUrl = miniUrl;
            }

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            public int getPictureId() {
                return pictureId;
            }

            public void setPictureId(int pictureId) {
                this.pictureId = pictureId;
            }
        }
    }
}
