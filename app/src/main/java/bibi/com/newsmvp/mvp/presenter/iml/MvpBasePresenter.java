package bibi.com.newsmvp.mvp.presenter.iml;

import bibi.com.newsmvp.mvp.presenter.MvpPresenter;
import bibi.com.newsmvp.mvp.view.MvpView;

/**
 * Created by bibinet on 2016-12-27.
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V view;

    @Override
    public void attchview(V view) {
        this.view = view;
    }

    @Override
    public void deattchview() {
        if (view != null) {
            view = null;
        }
    }
}
