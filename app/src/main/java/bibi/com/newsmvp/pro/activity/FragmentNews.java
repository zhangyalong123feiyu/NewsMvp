package bibi.com.newsmvp.pro.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.TabFragmentAdapter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNews extends BaseFragment {

    private View view;
    private TabLayout tablayout;
    private ViewPager newsviewpager;
    public Fragment topfragement = new FragmentTop();
    public Fragment socialfragement = new FragmentSocial();
    public Fragment heathyfragement = new FragmentHeathy();
    public Fragment worldfragement = new FragmentWorld();
    public Fragment videofragement = new FragmentVideoNba();
    private ImageView add;
    private ArrayList<Fragment> fragemtslists;

    public FragmentNews() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initview();
        setlistioner();
        return view;
    }

    private void setlistioner() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChannelActivity.class));
            }
        });
    }

    private void initview() {
        add = (ImageView) view.findViewById(R.id.add);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        newsviewpager = (ViewPager) view.findViewById(R.id.newviewpager);
        //  floatbutton=(FloatingActionButton)view.findViewById(R.id.fab);
        newsviewpager.setOffscreenPageLimit(3);//设置缓存页面和当前页面
        //tab 相关操作
        List<String> tabList = new ArrayList<>();
        tabList.add(getString(R.string.tab1));
        tabList.add(getString(R.string.tab2));
        tabList.add(getString(R.string.tab3));
        tabList.add(getString(R.string.tab4));
        tabList.add(getString(R.string.tab5));

     /*   tabList.add(getString(R.string.tab5));
        tabList.add(getString(R.string.tab6));
        tabList.add(getString(R.string.tab7));
        tabList.add(getString(R.string.tab8));
        tabList.add(getString(R.string.tab9));
        tabList.add(getString(R.string.tab10));*/

        tablayout.addTab(tablayout.newTab().setText(tabList.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(1)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(2)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(3)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(4)));


       /* tablayout.addTab(tablayout.newTab().setText(tabList.get(4)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(5)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(6)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(7)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(8)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(9)));*/


        fragemtslists = new ArrayList<>();
        fragemtslists.add(topfragement);
        fragemtslists.add(socialfragement);
        fragemtslists.add(heathyfragement);
        fragemtslists.add(worldfragement);
        fragemtslists.add(videofragement);


        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getActivity().getSupportFragmentManager(), fragemtslists, tabList);
        newsviewpager.setAdapter(fragmentAdapter);
        tablayout.setupWithViewPager(newsviewpager);//关联viewpager和tab
        //tablayout.setTabsFromPagerAdapter(fragmentAdapter);//给tabs设置adapter

    }

}
