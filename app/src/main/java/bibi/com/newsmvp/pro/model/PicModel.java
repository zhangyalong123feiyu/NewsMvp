package bibi.com.newsmvp.pro.model;

import android.content.Context;

import org.xutils.http.RequestParams;
import org.xutils.x;

import bibi.com.newsmvp.pro.base.model.BaseModel;
import bibi.com.newsmvp.pro.builder.MyCallBack;

/**
 * Created by bibinet on 2017-1-6.
 */
public class PicModel extends BaseModel {
    public PicModel(Context context) {
        super(context);
    }

    public void getdata(int page, MyCallBack myCallBack) {
        RequestParams requestParams = new RequestParams("http://api.tianapi.com/meinv/");
        requestParams.addBodyParameter("key", "f970d00798f1c3566388bf4af2fa6bac");
        requestParams.addBodyParameter("num", "30");
        requestParams.addBodyParameter("page", String.valueOf(page));
        x.http().get(requestParams, myCallBack);
    }
}
