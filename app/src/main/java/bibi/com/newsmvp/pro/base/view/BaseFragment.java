package bibi.com.newsmvp.pro.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.MvpView;
import bibi.com.newsmvp.mvp.view.iml.MvpFragement;

/**
 * Created by bibinet on 2016-12-27.
 */
public class BaseFragment<P extends MvpBasePresenter> extends MvpFragement<P> {
    @Override
    public P bindpresenter() {
        return null;
    }


}
