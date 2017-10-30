package cn.shield.checkin.other;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 14:48<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class Common {

    public static final String sHOST = "http://www.baidu.com/";

    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getPhoneProduct() {
        return Build.BRAND;
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
