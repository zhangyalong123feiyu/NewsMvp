package bibi.com.newsmvp.pro.presenter;

import android.content.Context;

import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.model.H5Model;

/**
 * Created by Lenovo_user on 2017/1/5.
 */
public class H5Presenter extends BasePresenter<H5Model> {
    public H5Presenter(Context context) {
        super(context);
    }

    @Override
    public H5Model bindmodel() {
        return new H5Model(getContext());
    }

    public void loadData(OnUiThreadListioner onUiThreadListioner) {


    }

}
