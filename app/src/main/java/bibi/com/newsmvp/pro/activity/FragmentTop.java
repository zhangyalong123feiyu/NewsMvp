package bibi.com.newsmvp.pro.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.TopListAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.TopNewsBean;
import bibi.com.newsmvp.pro.presenter.TopPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTop extends BaseFragment<TopPresenter> {


    private View view;
    public RecyclerView recyclerview;
    public List<TopNewsBean.NewsResult.NewInfo> newsinfo;
    private FloatingActionButton floatbutton;
    private TopListAdapter adapter;
    private boolean isvisible;
    private boolean isFirstLoad = true;
    private boolean isprepared;

    @Override
    public TopPresenter bindpresenter() {
        return new TopPresenter(getContext());
    }

    public FragmentTop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);
        initview();
        isprepared = true;
        setlistioner();
        layzyLoadData();
        return view;
    }

    private void setlistioner() {
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isvisible = true;
            onvisible();
        } else {
            isvisible = false;
            invisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isvisible = true;
            onvisible();
        } else {
            isvisible = false;
            invisible();
        }
    }

    private void invisible() {

    }

    private void onvisible() {
        layzyLoadData();
    }

    private void layzyLoadData() {
        if (isvisible && isprepared && isFirstLoad) {
            isFirstLoad = false;
            initdata();
        }
    }

    private void initview() {
        floatbutton = (FloatingActionButton) view.findViewById(R.id.fab);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void initdata() {
        presenter.loadata(new BasePresenter.OnUiThreadListioner<TopNewsBean>() {
            @Override
            public void OnSuccess(TopNewsBean topNewsBean) {
                newsinfo = topNewsBean.getResult().getData();
                Log.i("TAG", newsinfo.toString());
                adapter = new TopListAdapter(newsinfo, getActivity());
                recyclerview.setAdapter(adapter);

            }

            @Override
            public void OnFailed(String errorinfo) {
                Log.i("TAG", errorinfo);
            }
        });

    }

}
