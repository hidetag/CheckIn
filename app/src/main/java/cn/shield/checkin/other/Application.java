package cn.shield.checkin.other;

import android.content.Context;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 13:57<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class Application extends android.app.Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
