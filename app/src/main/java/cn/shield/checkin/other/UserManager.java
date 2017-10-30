package cn.shield.checkin.other;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 13:54<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public enum UserManager {

    INSTANCE;

    public static final String sNAME = "name";
    public static final String sPSW = "psw";
    public static final String sID = "id";
    public static final String sTOKEN = "token";

    public void saveInfo(String key, String content) {
        PreferenceUtils.save(key, content);
    }

    public String getInfo(String key) {
        return PreferenceUtils.get(key, "");
    }
}
