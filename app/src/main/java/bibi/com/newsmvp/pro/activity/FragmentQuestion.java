package bibi.com.newsmvp.pro.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.adapter.ChatAdapter;
import bibi.com.newsmvp.pro.base.presenter.BasePresenter;
import bibi.com.newsmvp.pro.base.view.BaseFragment;
import bibi.com.newsmvp.pro.bean.ChatBean;
import bibi.com.newsmvp.pro.bean.RoobtResult;
import bibi.com.newsmvp.pro.presenter.QustionPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentQuestion extends BaseFragment<QustionPresenter> {


    private View view;
    private RecyclerView questionrecylerview;
    private Button buttonsend;
    private EditText edit_input;
    //private List<String> Roobtcontentlist=new ArrayList<>();
    private List<ChatBean> datas = new ArrayList<>();
    private ChatAdapter adapter;
    private FragmentActivity activity;
    private String mecontent;

    @Override
    public QustionPresenter bindpresenter() {
        return new QustionPresenter(getContext());
    }

    public FragmentQuestion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_weather, container, false);
        initview();
        initlistioner();
        return view;
    }

    private void initlistioner() {
        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                edit_input.setText("");
            }
        });
    }


    private void initview() {
        activity = getActivity();
        buttonsend = (Button) view.findViewById(R.id.sendbutton);
        edit_input = (EditText) view.findViewById(R.id.input_edit);
        questionrecylerview = (RecyclerView) view.findViewById(R.id.questionrecyclerview);
        questionrecylerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    private void initData() {
        mecontent = edit_input.getText().toString().trim();

        datas.add(new ChatBean(new RoobtResult(mecontent)));
        presenter.getRespose(mecontent, new BasePresenter.OnUiThreadListioner<ChatBean>() {
            @Override
            public void OnSuccess(ChatBean chatinfo) {
                // String roobtcontent = chatinfo.getResult().getText();
                datas.add(chatinfo);
                if (adapter == null) {
                    adapter = new ChatAdapter(datas, getActivity());
                    questionrecylerview.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                    questionrecylerview.smoothScrollToPosition(adapter.getItemCount());
                }

            }

            @Override
            public void OnFailed(String errorinfo) {

            }
        });
    }

}
