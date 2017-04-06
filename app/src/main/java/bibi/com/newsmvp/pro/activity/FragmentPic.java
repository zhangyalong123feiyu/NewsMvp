package bibi.com.newsmvp.pro.activity;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.PicAdapter;
import bibi.com.newsmvp.pro.adapter.SocailFooterAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.ImageBean;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.presenter.PicPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPic extends BaseFragment<PicPresenter> {

    private View view;
    private RecyclerView recyclerview;
    private List<ImageBean.Picinfo> picinfos;
    private SwipeRefreshLayout refreshlayout;
    private int page = 1;
    private PicAdapter adapter;
    private int lastVisibleItem;

    @Override
    public PicPresenter bindpresenter() {
        return new PicPresenter(getContext());
    }

    public FragmentPic() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pic, container, false);
        initvew();
        initData(false);
        return view;
    }

    private void initvew() {
        refreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.picrefresh);
        recyclerview = (RecyclerView) view.findViewById(R.id.picrecylerview);
        recyclerview.setHasFixedSize(true);
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerview.addItemDecoration(decoration);
        recyclerview.setHasFixedSize(true);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(false);
            }
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                //  lastVisibleItem = staggeredGridLayoutManager.findLastVisibleItemPositions();
                int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItem = findMax(lastPositions);
            }
        });
        //socialrecylerview.setLayoutManager(layoutManager);
        refreshlayout.setRefreshing(true);

    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    private void initData(final boolean isLoadMore) {
        Log.d("test", page + "-----------");
        if (isLoadMore) {
            adapter.changeMoreStatus(SocailFooterAdapter.LOADING_MORE);
            page++;
        } else {
            page = 1;
        }
        presenter.initdata(page, new BasePresenter.OnUiThreadListioner<ImageBean>() {
            List<ImageBean.Picinfo> msgs;

            @Override
            public void OnSuccess(ImageBean picinfo) {
                msgs = picinfo.getNewslist();
                if (isLoadMore) {
                    Log.i("TAG", "44444");
                    if (msgs.size() == 0) {
                        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                        // infoListView.scrollToPosition(InfoRefreshFootAdapter.Lastposition);
                        recyclerview.smoothScrollToPosition(adapter.getItemCount() - 1);
                        // infoListView.smoothScrollBy(240,1000);
                    } else {
                        Log.i("TAG", "55555555555");
                        adapter.addMoreItem(msgs);
                        adapter.changeMoreStatus(SocailFooterAdapter.PULLUP_LOAD_MORE);
                    }
                } else {
                    Log.i("TAG", "66666");
                    adapter = new PicAdapter(getActivity(), msgs);
                    recyclerview.setAdapter(adapter);
                    recyclerview.scrollToPosition(0);
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
