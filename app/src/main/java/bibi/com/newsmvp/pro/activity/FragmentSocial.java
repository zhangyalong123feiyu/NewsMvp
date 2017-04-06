package bibi.com.newsmvp.pro.activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.SocailFooterAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.presenter.SocialPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSocial extends BaseFragment<SocialPresenter> {


    private View view;
    private RecyclerView socialrecylerview;
    private List<SocailBean.SocailInfo> SocailInfolist;
    private SwipeRefreshLayout refreshlayout;
    private Handler handler = new Handler();
    private SocailFooterAdapter adapter;
    private int page = 1;
    private boolean isFirst = true;
    private FragmentActivity activity;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public SocialPresenter bindpresenter() {
        return new SocialPresenter(getContext());
    }

    public FragmentSocial() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nba, container, false);
        initview();
        initData(false);
        activity = getActivity();
        return view;
    }

    private void initview() {
        refreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.socialrefresh);
        socialrecylerview = (RecyclerView) view.findViewById(R.id.socailreyclerview);
        linearLayoutManager = new LinearLayoutManager(activity);
        socialrecylerview.setLayoutManager(linearLayoutManager);

        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(false);
            }
        });
        socialrecylerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    initData(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        //socialrecylerview.setLayoutManager(layoutManager);
        refreshlayout.setRefreshing(true);
    }

    private void initData(final boolean isLoadMore) {
        Log.d("test", page + "-----------");
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            page++;
        } else {
            page = 1;
        }
        presenter.getData(page, new BasePresenter.OnUiThreadListioner<SocailBean>() {
            List<SocailBean.SocailInfo> msgs;

            @Override
            public void OnSuccess(SocailBean socialinfo) {
                msgs = socialinfo.getNewslist();
                if (isLoadMore) {
                    if (msgs.size() == 0) {
                        Toast.makeText(activity, "没有更多数据", Toast.LENGTH_SHORT).show();
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                        // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                        socialrecylerview.smoothScrollToPosition(adapter.getItemCount() - 1);
                        // infoListView.smoothScrollBy(240,1000);
                    } else {
                        adapter.addMoreItem(msgs);
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                    }
                } else {
                    adapter = new SocailFooterAdapter(activity, msgs);
                    socialrecylerview.setAdapter(adapter);
                    refreshlayout.setRefreshing(false);
                }
            }

            @Override
            public void OnFailed(String errorinfo) {
                Log.e("TAG", errorinfo);
                refreshlayout.setRefreshing(false);
            }
        });


    }


}
