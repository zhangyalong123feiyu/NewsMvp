package bibi.com.newsmvp.pro.model;

import android.content.Context;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bibi.com.newsmvp.pro.base.model.BaseModel;
import bibi.com.newsmvp.pro.builder.MyCallBack;

/**
 * Created by Lenovo_user on 2016/12/27.
 */
public class TopModel extends BaseModel {
    public TopModel(Context context) {
        super(context);
    }

    public void loaddata(MyCallBack myCallBack) {
        RequestParams requestParams = new RequestParams("http://v.juhe.cn/toutiao/index");
        requestParams.addBodyParameter("key", "930b6600d6904c33297912a9790598f8");
        x.http().get(requestParams, myCallBack);
    }
}
