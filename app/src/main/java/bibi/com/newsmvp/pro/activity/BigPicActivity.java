package bibi.com.newsmvp.pro.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.iml.MvpActivity;

/**
 * Created by bibinet on 2017-1-6.
 */
public class BigPicActivity extends MvpActivity {
    @Override
    public MvpBasePresenter bindpresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigpic);
        Intent intent = getIntent();
        ImageView picimage = (ImageView) findViewById(R.id.bigpic);
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "a");
    /*  Glide.with(this).load(intent.getStringExtra("picurl")).placeholder(R.drawable.biz_plugin_weather_baoxue)
              .transform(new stagbi).into(picimage);*/
        Picasso.with(this)
                .load(intent.getStringExtra("picurl"))
                .placeholder(R.drawable.biz_plugin_weather_baoxue)
                .into(picimage);
        // picimage.setImageBitmap(bitmap);
        ViewCompat.setTransitionName(picimage, "123");


    }

}
