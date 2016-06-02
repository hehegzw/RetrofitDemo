package gzw.cn.retrofit.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gzw on 2016/6/1.
 */
public interface APIServiceGet {
    @GET("/UploadFile/{action}")
    Call<ResponseBody> getData(@Path("action") String action);
}
