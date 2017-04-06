package bibi.com.newsmvp.mvp.view.iml;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.MvpView;

/**
 * Created by bibinet on 2016-12-27.
 */
public abstract class MvpFragement<P extends MvpBasePresenter> extends Fragment implements MvpView {
    public P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = bindpresenter();
        if (presenter != null) {
            presenter.attchview(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.deattchview();
        }
    }

    public abstract P bindpresenter();
}
