package bibi.com.newsmvp.pro.presenter;

import android.content.Context;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.bean.VideoBean;
import bibi.com.newsmvp.pro.builder.MyCallBack;
import bibi.com.newsmvp.pro.model.VideoModel;

/**
 * Created by bibinet on 2017-1-9.
 */
public class VideoPresenter extends BasePresenter<VideoModel> {
    public VideoPresenter(Context context) {
        super(context);
    }

    @Override
    public VideoModel bindmodel() {
        return new VideoModel(getContext());
    }

    public void getVideoInfo(int page, final OnUiThreadListioner onUiThreadListioner) {
        getModel().getData(page, new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                onUiThreadListioner.OnSuccess(getGson().fromJson(s, SocailBean.class));
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }
        });
    }
}
