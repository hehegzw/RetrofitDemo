package gzw.cn.retrofit.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import gzw.cn.retrofit.MyApp;
import gzw.cn.retrofit.R;
import gzw.cn.retrofit.api.APIServerGetDP;
import gzw.cn.retrofit.api.APIServiceFile;
import gzw.cn.retrofit.api.APIServiceForm;
import gzw.cn.retrofit.api.APIServiceGet;
import gzw.cn.retrofit.api.APIServiceGetJson;
import gzw.cn.retrofit.databinding.Main;
import gzw.cn.retrofit.model.User;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Retrofit retrofit;
    private Main bind;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        retrofit = MyApp.getRetrofit();
        context = this;
        initEvent();
    }

    private void initEvent() {
        bind.btnGet.setOnClickListener(this);
        bind.btnGetJson.setOnClickListener(this);
        bind.btnGetJsonDp.setOnClickListener(this);
        bind.btnGetForm.setOnClickListener(this);
        bind.btnUploadFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                get();
                break;
            case R.id.btn_get_json:
                getJson();
                break;
            case R.id.btn_get_json_dp:
                getJsonDp();
                break;
            case R.id.btn_get_form:
                getForm();
                break;
            case R.id.btn_upload_file:
                uploadFile();
                break;
        }
    }
    private void get() {
        APIServiceGet server = retrofit.create(APIServiceGet.class);
        Call<ResponseBody> call = server.getData("Action");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Logger.d(MyApp.TAG,"success");
                try {
                    bind.tvGet.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.d(MyApp.TAG,t);
            }
        });
    }
    private void getJson(){
        APIServiceGetJson server = retrofit.create(APIServiceGetJson.class);
        Call<User> call = server.getUser("Action");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()==null){
                    //Logger.d(MyApp.TAG,"解析出错");
                }
                bind.tvGetJson.setText(response.body().username+":"+response.body().password);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Logger.d(MyApp.TAG,t);
            }
        });
    }
    private void getJsonDp(){
        APIServerGetDP server = retrofit.create(APIServerGetDP.class);
        String param = bind.etParam.getText().toString();
        if(TextUtils.isEmpty(param)) return;
        Call<ResponseBody> call = server.getUser("GetParam",param);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()==null){
                    //Logger.d(MyApp.TAG,"解析出错");
                }
                try {
                    bind.tvGetJsonDp.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Logger.d(t.getMessage(),null);
            }
        });
    }
    private void getForm(){
        APIServiceForm server = retrofit.create(APIServiceForm.class);
        String username = bind.etParam1.getText().toString();
        String password = bind.etParam2.getText().toString();
        Call<User> call = server.getUser("GetForm",username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body()==null){
                       // Logger.d(MyApp.TAG,"解析出错");
                    }
                    bind.tvGetForm.setText(response.body().username+":"+response.body().password);
                }else{
                   // Logger.d("网络访问出错","解析出错");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Logger.d(MyApp.TAG,t);
            }
        });
    }
    private void uploadFile(){
        APIServiceFile server = retrofit.create(APIServiceFile.class);
        File file = getFile();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        Call<ResponseBody> call = server.sendFile("UploadFile","image",requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context,"上传文件成功",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,"上传文件失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private File getFile(){
        try {
            InputStream in = getResources().getAssets().open("image.jpg");
            File file = new File(getCacheDir().getPath()+"/image.jpg");
            if(!file.exists()){
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = in.read(bytes))>0) {
                    out.write(bytes,0,len);
                }
                out.flush();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
