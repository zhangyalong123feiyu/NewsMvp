package bibi.com.newsmvp.pro.activity;


import android.nfc.Tag;
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

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.SocailFooterAdapter;
import bibi.com.newsmvp.pro.adapter.VideoListAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.bean.VideoBean;
import bibi.com.newsmvp.pro.presenter.VideoPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVideoNba extends BaseFragment<VideoPresenter> {


    private View view;
    private SwipeRefreshLayout videorefreshlayout;
    private RecyclerView videorecylerview;
    private List<SocailBean.SocailInfo> infolist = new ArrayList<>();
    private int page;
    private SocailFooterAdapter adapter;
    private int lastvisibleview;

    public FragmentVideoNba() {
        // Required empty public constructor
    }

    @Override
    public VideoPresenter bindpresenter() {
        return new VideoPresenter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video_nba, container, false);
        initview();
        initData(false);
        return view;
    }


    private void initview() {
        videorefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.videorefreshlayout);
        videorecylerview = (RecyclerView) view.findViewById(R.id.videorecyclerview);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videorecylerview.setLayoutManager(linearLayoutManager);
        videorefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(false);
            }
        });
        videorecylerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisibleview + 1 == adapter.getItemCount()) {
                    initData(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastvisibleview = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        videorefreshlayout.setRefreshing(true);

    }

    private void initData(final boolean isLoadMore) {
        Log.d("test", page + "-----------");
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            page++;
        } else {
            page = 1;
        }
        presenter.getVideoInfo(page, new BasePresenter.OnUiThreadListioner<SocailBean>() {
            List<SocailBean.SocailInfo> msgs;

            @Override
            public void OnSuccess(SocailBean socialinfo) {
                msgs = socialinfo.getNewslist();
                if (isLoadMore) {
                    if (msgs.size() == 0) {
                        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                        // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                        videorecylerview.smoothScrollToPosition(adapter.getItemCount() - 1);
                        // infoListView.smoothScrollBy(240,1000);
                    } else {
                        adapter.addMoreItem(msgs);
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                    }
                } else {
                    adapter = new SocailFooterAdapter(getActivity(), msgs);
                    videorecylerview.setAdapter(adapter);
                    videorefreshlayout.setRefreshing(false);
                }
            }

            @Override
            public void OnFailed(String errorinfo) {
                Log.e("TAG", errorinfo);
                videorefreshlayout.setRefreshing(false);
            }
        });


    }


}
