package bibi.com.newsmvp.pro.presenter;

import android.content.Context;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.builder.MyCallBack;
import bibi.com.newsmvp.pro.model.SocialModel;

/**
 * Created by bibinet on 2017-1-6.
 */
public class SocialPresenter extends BasePresenter<SocialModel> {
    public SocialPresenter(Context context) {
        super(context);
    }

    @Override
    public SocialModel bindmodel() {
        return new SocialModel(getContext());
    }

    public void getData(int page, final OnUiThreadListioner onUiThreadListioner) {
        getModel().getData(page, new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                SocailBean socailinfo = getGson().fromJson(s, SocailBean.class);
                onUiThreadListioner.OnSuccess(socailinfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }
        });
    }
}
