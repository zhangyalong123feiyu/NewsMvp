package bibi.com.newsmvp.pro.presenter;

import android.content.Context;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.bean.ChatBean;
import bibi.com.newsmvp.pro.builder.MyCallBack;
import bibi.com.newsmvp.pro.model.QustionModel;

/**
 * Created by bibinet on 2017-1-7.
 */
public class QustionPresenter extends BasePresenter<QustionModel> {
    public QustionPresenter(Context context) {
        super(context);
    }

    @Override
    public QustionModel bindmodel() {
        return new QustionModel(getContext());
    }

    public void getRespose(String content, final OnUiThreadListioner onUiThreadListioner) {
        getModel().getRoobtContent(new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ChatBean chatinfo = getGson().fromJson(s, ChatBean.class);
                onUiThreadListioner.OnSuccess(chatinfo);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }
        }, content);

    }
}
