package cn.shield.checkin.net;

import cn.shield.checkin.model.CheckInResponse;
import cn.shield.checkin.model.LoginResponse;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:36<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public interface API {

    @FormUrlEncoded
    @POST("xxx")
    Flowable<LoginResponse> login(@Field("loginname") String loginname, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("xxx")
    Flowable<CheckInResponse> checkIn(
            @Field("user_id") String user_id,
            @Field("token") String token,
            @Field("company_id") String company_id,
            @Field("type") String type,
            @Field("location") String location,
            @Field("longitude") double longitude,
            @Field("latitude") double latitude,
            @Field("wifi_name") String wifi_name,
            @Field("wifi_mac") String wifi_mac,
            @Field("file_id") String file_id,
            @Field("remark") String remark,
            @Field("is_force") int is_force,
            @Field("device_name") String device_name,
            @Field("device_no") String device_no,
            @Field("date") String date
    );
}
