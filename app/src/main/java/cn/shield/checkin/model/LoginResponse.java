package cn.shield.checkin.model;

import java.util.List;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:43<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class LoginResponse {
    private String result;
    private DataBean data;
    private int is_new_user;
    private int is_verify;
    private String check_hint;
    private String jwt;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getIs_new_user() {
        return is_new_user;
    }

    public void setIs_new_user(int is_new_user) {
        this.is_new_user = is_new_user;
    }

    public int getIs_verify() {
        return is_verify;
    }

    public void setIs_verify(int is_verify) {
        this.is_verify = is_verify;
    }

    public String getCheck_hint() {
        return check_hint;
    }

    public void setCheck_hint(String check_hint) {
        this.check_hint = check_hint;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static class DataBean {
        private String modified;
        private String weixin_secret;
        private String xxx_id;
        private OtherInfoBean other_info;
        private String weixin_uid;
        private String nickname;
        private String from_app;
        private String _id;
        private String weixin_unionid;
        private int is_new_user;
        private String weixin_nickname;
        private int weixin_valid;
        private String token;
        private String last_login_date;
        private String weixin_token;
        private String avatar;
        private String nickname_en;
        private String created;
        private int is_comuser;
        private List<CompanyBean> company;
        private List<String> phones;
        private List<String> device_token;

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getWeixin_secret() {
            return weixin_secret;
        }

        public void setWeixin_secret(String weixin_secret) {
            this.weixin_secret = weixin_secret;
        }

        public String getxxx_id() {
            return xxx_id;
        }

        public void setxxx_id(String xxx_id) {
            this.xxx_id = xxx_id;
        }

        public OtherInfoBean getOther_info() {
            return other_info;
        }

        public void setOther_info(OtherInfoBean other_info) {
            this.other_info = other_info;
        }

        public String getWeixin_uid() {
            return weixin_uid;
        }

        public void setWeixin_uid(String weixin_uid) {
            this.weixin_uid = weixin_uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFrom_app() {
            return from_app;
        }

        public void setFrom_app(String from_app) {
            this.from_app = from_app;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getWeixin_unionid() {
            return weixin_unionid;
        }

        public void setWeixin_unionid(String weixin_unionid) {
            this.weixin_unionid = weixin_unionid;
        }

        public int getIs_new_user() {
            return is_new_user;
        }

        public void setIs_new_user(int is_new_user) {
            this.is_new_user = is_new_user;
        }

        public String getWeixin_nickname() {
            return weixin_nickname;
        }

        public void setWeixin_nickname(String weixin_nickname) {
            this.weixin_nickname = weixin_nickname;
        }

        public int getWeixin_valid() {
            return weixin_valid;
        }

        public void setWeixin_valid(int weixin_valid) {
            this.weixin_valid = weixin_valid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLast_login_date() {
            return last_login_date;
        }

        public void setLast_login_date(String last_login_date) {
            this.last_login_date = last_login_date;
        }

        public String getWeixin_token() {
            return weixin_token;
        }

        public void setWeixin_token(String weixin_token) {
            this.weixin_token = weixin_token;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname_en() {
            return nickname_en;
        }

        public void setNickname_en(String nickname_en) {
            this.nickname_en = nickname_en;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getIs_comuser() {
            return is_comuser;
        }

        public void setIs_comuser(int is_comuser) {
            this.is_comuser = is_comuser;
        }

        public List<CompanyBean> getCompany() {
            return company;
        }

        public void setCompany(List<CompanyBean> company) {
            this.company = company;
        }

        public List<String> getPhones() {
            return phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public List<String> getDevice_token() {
            return device_token;
        }

        public void setDevice_token(List<String> device_token) {
            this.device_token = device_token;
        }

        public static class OtherInfoBean {

            private String city;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }

        public static class CompanyBean {
            private String creator_nickname;
            private String company_name;
            private String _id;
            private String company_alias;
            private String show_id;
            private String company_logo;

            public String getCreator_nickname() {
                return creator_nickname;
            }

            public void setCreator_nickname(String creator_nickname) {
                this.creator_nickname = creator_nickname;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCompany_alias() {
                return company_alias;
            }

            public void setCompany_alias(String company_alias) {
                this.company_alias = company_alias;
            }

            public String getShow_id() {
                return show_id;
            }

            public void setShow_id(String show_id) {
                this.show_id = show_id;
            }

            public String getCompany_logo() {
                return company_logo;
            }

            public void setCompany_logo(String company_logo) {
                this.company_logo = company_logo;
            }
        }
    }
}
