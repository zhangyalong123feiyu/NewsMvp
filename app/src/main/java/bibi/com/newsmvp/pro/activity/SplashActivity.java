package bibi.com.newsmvp.pro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.iml.MvpActivity;

/**
 * Created by bibinet on 2017-1-14.
 */
public class SplashActivity extends MvpActivity {
    private ImageView imageView;
    private AnimationSet aniset;

    @Override
    public MvpBasePresenter bindpresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.longbg);
        initanimatin();
        setContentView(imageView);
        initlistioner();
    }

    private void initlistioner() {
        aniset.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initanimatin() {
        aniset = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation(

                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,

                Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        aniset.addAnimation(translate);

        aniset.setFillAfter(true);

        translate.setDuration(2000);
        translate.setFillAfter(true);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        aniset.addAnimation(translate);
        aniset.addAnimation(rotateAnimation);
        imageView.startAnimation(aniset);
        aniset.start();
    }

}
