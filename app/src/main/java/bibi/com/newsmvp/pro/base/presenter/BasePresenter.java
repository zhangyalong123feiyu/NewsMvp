package bibi.com.newsmvp.pro.base.presenter;

import android.content.Context;

import com.google.gson.Gson;

import bibi.com.newsmvp.mvp.model.iml.MvpBaseModel;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.pro.base.model.BaseModel;

/**
 * Created by bibinet on 2016-12-27.
 * 用来处理数据业务逻辑
 */
public abstract class BasePresenter<M extends BaseModel> extends MvpBasePresenter {
    private Context context;
    private Gson gson;
    private M model;

    public BasePresenter(Context context) {
        this.context = context;
        gson = new Gson();
        model = bindmodel();
    }

    public abstract M bindmodel();

    public Context getContext() {
        return context;
    }

    public Gson getGson() {
        return gson;
    }

    public M getModel() {
        return model;
    }

    public interface OnUiThreadListioner<T> {
        void OnSuccess(T t);

        void OnFailed(String errorinfo);

    }
}
