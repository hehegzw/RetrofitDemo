package gzw.cn.retrofit.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import gzw.cn.retrofit.R;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.d("Main3",width+":"+height);
    }
}
