package bibi.com.newsmvp.pro.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.SocailFooterAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.presenter.WorldPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWorld extends BaseFragment<WorldPresenter> {
    private View view;
    private RecyclerView heathreyclerview;
    private List<SocailBean.SocailInfo> infolist;
    private SwipeRefreshLayout refreshlayout;
    private int page = 1;
    private SocailFooterAdapter adapter;
    private int lastvisibleitem;

    @Override
    public WorldPresenter bindpresenter() {
        return new WorldPresenter(getContext());
    }

    public FragmentWorld() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_car, container, false);
        initview();
        initData(false);
        return view;
    }


    private void initview() {
        refreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.wordrefreshlayout);
        heathreyclerview = (RecyclerView) view.findViewById(R.id.heathrecyclerview);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        heathreyclerview.setLayoutManager(linearLayoutManager);
        refreshlayout.setRefreshing(true);
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(false);
            }
        });
        heathreyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleitem + 1 == adapter.getItemCount()) {
                    initData(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastvisibleitem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

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
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                        // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                        heathreyclerview.smoothScrollToPosition(adapter.getItemCount() - 1);
                        // infoListView.smoothScrollBy(240,1000);
                    } else {
                        adapter.addMoreItem(msgs);
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                    }
                } else {
                    adapter = new SocailFooterAdapter(getActivity(), msgs);
                    heathreyclerview.setAdapter(adapter);
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
