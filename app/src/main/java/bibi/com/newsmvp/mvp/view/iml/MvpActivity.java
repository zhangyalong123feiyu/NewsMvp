package bibi.com.newsmvp.mvp.view.iml;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.MvpView;

/**
 * Created by bibinet on 2016-12-27.
 */
public abstract class MvpActivity<P extends MvpBasePresenter> extends AppCompatActivity implements MvpView {
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = bindpresenter();
        if (presenter != null) {
            presenter.attchview(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.deattchview();
        }
    }

    public abstract P bindpresenter();

}
