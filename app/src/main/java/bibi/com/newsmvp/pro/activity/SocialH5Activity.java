package bibi.com.newsmvp.pro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.iml.MvpActivity;

/**
 * Created by bibinet on 2017-1-6.
 */
public class SocialH5Activity extends MvpActivity {

    private WebView webView;
    private String socialurl;

    @Override
    public MvpBasePresenter bindpresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socailh5);
        initview();
        lodeData();

    }

    private void initview() {
        Intent intent = getIntent();
        socialurl = intent.getStringExtra("socialdetail");
        webView = (WebView) findViewById(R.id.socialh5webview);
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
        webView.loadUrl(socialurl);
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
            return false;
        }
        SocialH5Activity.this.finish();
        return true;
    }
}
