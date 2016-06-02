package gzw.cn.retrofit;

import android.app.Application;

import com.orhanobut.logger.Logger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gzw on 2016/6/1.
 */
public class MyApp extends Application {
    public static final String TAG = "MyApp";
    private static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
        Logger.init(TAG);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
