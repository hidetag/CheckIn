package cn.shield.checkin.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:24<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class OkHttpHelper {
    private static OkHttpClient mClient;

    public static OkHttpClient getDefault() {
        if (mClient == null) {
            synchronized (OkHttpHelper.class) {
                if (mClient == null) {
                    mClient = create();
                }
            }
        }
        return mClient;
    }

    private static OkHttpClient create() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        return builder.build();
    }


}

