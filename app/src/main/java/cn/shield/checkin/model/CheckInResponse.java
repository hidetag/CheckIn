package cn.shield.checkin.model;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:55<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class CheckInResponse {

    private String result;
    private DataBean data;

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

    public static class DataBean {
        private String company_id;
        private String user_id;
        private String nickname;
        private int type;
        private String date;
        private String time;
        private String content;
        private String rule_time;
        private int rule_index;
        private int rule_duty_num;
        private String location;
        private String lat;
        private String lng;
        private String device_name;
        private String device_no;
        private int is_force;
        private String _id;
        private int keep_normal_day;
        private int today_duty_rank;

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRule_time() {
            return rule_time;
        }

        public void setRule_time(String rule_time) {
            this.rule_time = rule_time;
        }

        public int getRule_index() {
            return rule_index;
        }

        public void setRule_index(int rule_index) {
            this.rule_index = rule_index;
        }

        public int getRule_duty_num() {
            return rule_duty_num;
        }

        public void setRule_duty_num(int rule_duty_num) {
            this.rule_duty_num = rule_duty_num;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_no() {
            return device_no;
        }

        public void setDevice_no(String device_no) {
            this.device_no = device_no;
        }

        public int getIs_force() {
            return is_force;
        }

        public void setIs_force(int is_force) {
            this.is_force = is_force;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getKeep_normal_day() {
            return keep_normal_day;
        }

        public void setKeep_normal_day(int keep_normal_day) {
            this.keep_normal_day = keep_normal_day;
        }

        public int getToday_duty_rank() {
            return today_duty_rank;
        }

        public void setToday_duty_rank(int today_duty_rank) {
            this.today_duty_rank = today_duty_rank;
        }
    }
}
