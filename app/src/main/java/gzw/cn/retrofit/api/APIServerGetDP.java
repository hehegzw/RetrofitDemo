package gzw.cn.retrofit.api;

import gzw.cn.retrofit.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gzw on 2016/6/2.
 */
public interface APIServerGetDP {
    @GET("/UploadFile/{action}")
    Call<ResponseBody> getUser(@Path("action") String action, @Query("param") String one);
}
