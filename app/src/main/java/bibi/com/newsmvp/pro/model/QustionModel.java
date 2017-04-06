package bibi.com.newsmvp.pro.model;

import android.content.Context;

import org.xutils.http.RequestParams;
import org.xutils.x;

import bibi.com.newsmvp.pro.base.model.BaseModel;
import bibi.com.newsmvp.pro.builder.MyCallBack;

/**
 * Created by bibinet on 2017-1-7.
 */
public class QustionModel extends BaseModel {
    public QustionModel(Context context) {
        super(context);
    }

    public void getRoobtContent(MyCallBack myCallBack, String content) {
        RequestParams requestParams = new RequestParams("http://op.juhe.cn/robot/index");
        requestParams.addBodyParameter("key", "164c976e662351f7fbb915a98b9faed0");
        requestParams.addBodyParameter("info", content);
        x.http().get(requestParams, myCallBack);
    }
}
