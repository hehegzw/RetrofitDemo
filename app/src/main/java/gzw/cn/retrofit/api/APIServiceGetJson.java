package gzw.cn.retrofit.api;

import gzw.cn.retrofit.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gzw on 2016/6/2.
 */
public interface APIServiceGetJson {
    @GET("/UploadFile/{action}")
    Call<User> getUser(@Path("action") String action);
}
