package bibi.com.newsmvp.pro.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.iml.MvpActivity;
import bibi.com.newsmvp.pro.presenter.H5Presenter;

/**
 * Created by Lenovo_user on 2017/1/5.
 */
//@ContentView(R.layout.activity_h5)
public class H5Activity extends MvpActivity<H5Presenter> {

    //  @ViewInject(R.id.webview)
    private WebView webView;

    /*  @ViewInject(R.id.coordinalayout)
      private CoordinatorLayout coordinatorLayout;
      @ViewInject(R.id.appbar)
      private AppBarLayout appBarLayout;*/
    //   @ViewInject(R.id.title_image)
    private ImageView title_iamge;
    //   @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    private String newsurl;
    private String newtitle;
    private String newimageurl;

    @Override
    public H5Presenter bindpresenter() {
        return new H5Presenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        // x.view().inject(this);
        initview();
        lodeData();
    }

    private void initview() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webView = (WebView) findViewById(R.id.webview);
        title_iamge = (ImageView) findViewById(R.id.title_image);

        Intent intent = getIntent();
        newsurl = intent.getStringExtra("newdetailurl");
        newimageurl = intent.getStringExtra("newdetailimageurl");
        newtitle = intent.getStringExtra("newdetailtitle");

        toolbar.setTitle(newtitle);
        toolbar.setTitleTextColor(Color.argb(200, 5, 0, 0));

        setSupportActionBar(toolbar);

        Glide.with(this).load(newimageurl).crossFade().into(title_iamge);
        ViewCompat.setTransitionName(title_iamge, "123");
    }

    private void lodeData() {
        MyViewClient viewclient = new MyViewClient();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setSupportZoom(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl(newsurl);
        webView.setWebViewClient(viewclient);

    }

    //Web视图
    private class MyViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        H5Activity.this.finish();
        return false;
    }
}
