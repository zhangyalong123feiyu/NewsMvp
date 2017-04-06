package bibi.com.newsmvp.pro.presenter;

import android.content.Context;
import android.util.Log;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.bean.ImageBean;
import bibi.com.newsmvp.pro.builder.MyCallBack;
import bibi.com.newsmvp.pro.model.PicModel;

/**
 * Created by bibinet on 2017-1-6.
 */
public class PicPresenter extends BasePresenter<PicModel> {
    public PicPresenter(Context context) {
        super(context);
    }

    @Override
    public PicModel bindmodel() {
        return new PicModel(getContext());
    }

    public void initdata(int page, final OnUiThreadListioner onUiThreadListioner) {
        getModel().getdata(page, new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ImageBean iamgeinfo = getGson().fromJson(s, ImageBean.class);
                onUiThreadListioner.OnSuccess(iamgeinfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                Log.i("TAG", "error" + throwable.getMessage());
            }
        });
    }
}
