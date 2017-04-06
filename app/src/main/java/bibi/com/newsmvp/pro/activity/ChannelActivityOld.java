package bibi.com.newsmvp.pro.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.mvp.presenter.iml.MvpBasePresenter;
import bibi.com.newsmvp.mvp.view.iml.MvpActivity;
import bibi.com.newsmvp.pro.application.MyApplication;
import bibi.com.newsmvp.pro.bean.ChanelAdapter;
import bibi.com.newsmvp.pro.bean.ChannelTable;

/**
 * Created by bibinet on 2017-1-12.
 */
public class ChannelActivityOld extends MvpActivity {
    private RecyclerView myrecyclerview;
    private TextView eitchannel;
    private RecyclerView allchannelrecylerview;
    private DbManager db;
    private List<ChannelTable> data;
    private ChanelAdapter adapter;

    @Override
    public MvpBasePresenter bindpresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        Log.e("TAG", "----onCreate");
        initData();
        initview();
        setlistioner();
    }


    private void initData() {

        db = x.getDb(MyApplication.getDaoConfig());
        try {
            data = db.selector(ChannelTable.class).findAll();

            Log.e("TAG", data.size() + "--");
            Toast.makeText(this, data.size() + "--", Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            Log.e("TAG", "----initData");
            e.printStackTrace();
        }
    }

    private void initview() {
        myrecyclerview = (RecyclerView) findViewById(R.id.mychneelrecylerview);
        eitchannel = (TextView) findViewById(R.id.edit);
        allchannelrecylerview = (RecyclerView) findViewById(R.id.allchannelrecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        myrecyclerview.setLayoutManager(gridLayoutManager);
        adapter = new ChanelAdapter(this, data);
        myrecyclerview.setAdapter(adapter);
    }

    private void setlistioner() {
        eitchannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eitchannel.setSelected(true);
                adapter.setvisible();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (eitchannel.isSelected()) {
            eitchannel.setSelected(false);
        } else {
            super.onBackPressed();
        }
    }
}
