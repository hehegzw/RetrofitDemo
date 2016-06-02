package gzw.cn.retrofit.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by gzw on 2016/6/2.
 */
public interface APIServiceFile {
    @Multipart
    @POST("UploadFile/{action}")
    Call<ResponseBody> sendFile(@Path("action") String action,@Part("filename") String desc
            , @Part("file\";filename=\"image.jpg\"")RequestBody image);
}
