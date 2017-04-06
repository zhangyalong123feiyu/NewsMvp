package bibi.com.newsmvp.pro.application;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.config.DbConfigs;
import org.xutils.ex.DbException;
import org.xutils.x;

import bibi.com.newsmvp.pro.bean.ChannelTable;

/**
 * Created by Lenovo_user on 2016/12/27.
 */
public class MyApplication extends Application {
    private static DbManager.DaoConfig daoconfig;
    private DbManager db;

    public static DbManager.DaoConfig getDaoConfig() {
        return daoconfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        daoconfig = new DbManager.DaoConfig().setDbName("channel").setDbVersion(1).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager dbManager, int i, int i1) {

            }
        });
        db = x.getDb((daoconfig));
        try {
            db.save(new ChannelTable(1, "头条"));
            db.save(new ChannelTable(2, "社会"));
            db.save(new ChannelTable(3, "国内"));
            db.save(new ChannelTable(4, "国际"));
            db.save(new ChannelTable(5, "体育"));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
