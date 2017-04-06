package bibi.com.newsmvp.pro.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.bean.TopNewsBean;
import bibi.com.newsmvp.pro.builder.MyCallBack;
import bibi.com.newsmvp.pro.model.TopModel;

/**
 * Created by Lenovo_user on 2016/12/27.
 */
public class TopPresenter extends BasePresenter<TopModel> {
    public TopPresenter(Context context) {
        super(context);
    }

    @Override
    public TopModel bindmodel() {
        return new TopModel(getContext());
    }

    public void loadata(final OnUiThreadListioner<TopNewsBean> onUiThreadListioner) {
        getModel().loaddata(new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                TopNewsBean topnewsresult = getGson().fromJson(s, TopNewsBean.class);
                onUiThreadListioner.OnSuccess(topnewsresult);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
                Log.i("TAG", throwable.getMessage() + "----------");
                Toast.makeText(getContext(), "数据错误" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
