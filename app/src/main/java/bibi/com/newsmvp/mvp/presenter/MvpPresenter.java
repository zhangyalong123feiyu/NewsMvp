package bibi.com.newsmvp.mvp.presenter;

import bibi.com.newsmvp.mvp.view.MvpView;

/**
 * Created by bibinet on 2016-12-27.
 */
public interface MvpPresenter<V extends MvpView> {
    public void attchview(V view);

    public void deattchview();
}
