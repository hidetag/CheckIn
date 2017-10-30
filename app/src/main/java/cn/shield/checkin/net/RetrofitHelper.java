package cn.shield.checkin.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cn.shield.checkin.other.Common.sHOST;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:27<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class RetrofitHelper {

    private static Retrofit.Builder mRetrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpHelper.getDefault());

    public static Class sDefaultInterface = API.class;

    public static <T> T create(String api, Class<T> cls) {
        Retrofit retrofit = mRetrofit.baseUrl(api).build();
        return retrofit.create(cls);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createDefault() {
        Retrofit retrofit = mRetrofit.baseUrl(sHOST).build();
        return (T) retrofit.create(sDefaultInterface);
    }
}
