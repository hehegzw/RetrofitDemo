package gzw.cn.retrofit.api;

import gzw.cn.retrofit.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by gzw on 2016/6/2.
 */
public interface APIServiceForm {
    @FormUrlEncoded
    @POST("/UploadFile/{action}")
    Call<User> getUser(@Path("action") String action,@Field("username") String username
            ,@Field("password") String password);
}
