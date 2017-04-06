package bibi.com.newsmvp.pro.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.activity.BigPicActivity;
import bibi.com.newsmvp.pro.bean.ImageBean;
import bibi.com.newsmvp.pro.bean.VideoBean;

/**
 * Created by bibinet on 2017-1-10.
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    private List<VideoBean.ResultVideo.DayVideoInfo> dayVideoInfoList;

    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    private int Lastposition;

    public VideoListAdapter(Context context, List<VideoBean.ResultVideo.DayVideoInfo> dayVideoInfoList) {
        this.context = context;
        this.dayVideoInfoList = dayVideoInfoList;
        inflater = LayoutInflater.from(context);
        Toast.makeText(context, "成功adpter", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_video, parent, false);
            ItemHodler myViewHolder = new ItemHodler(view);
            return myViewHolder;
        } else {
            View foot_view = inflater.inflate(R.layout.progressbar_item, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ProgressViewHolder footViewHolder = new ProgressViewHolder(foot_view);
            return footViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHodler) {
            ItemHodler itemHolder = (ItemHodler) holder;
            String player1logopic = dayVideoInfoList.get(position).getTr().get(position).getPlayer1logo();
            String player2logopic = dayVideoInfoList.get(position).getTr().get(position).getPlayer2logo();
            ((ItemHodler) holder).gamedaytime.setText(dayVideoInfoList.get(position).getTr().get(position).getTime());
            ((ItemHodler) holder).player1name.setText(dayVideoInfoList.get(position).getTr().get(position).getPlayer1());
            ((ItemHodler) holder).player2name.setText(dayVideoInfoList.get(position).getTr().get(position).getPlayer2());
            ((ItemHodler) holder).gamedetailtime.setText(dayVideoInfoList.get(position).getTr().get(position).getTime());
            if (dayVideoInfoList.get(position).getTr().get(position).getStatus().equals("0")) {
                ((ItemHodler) holder).gamestatues.setText("未开播");
            } else if (dayVideoInfoList.get(position).getTr().get(position).getStatus().equals("1")) {
                ((ItemHodler) holder).gamestatues.setText("直播中");
            } else {
                ((ItemHodler) holder).gamestatues.setText("已结束");
            }
            ((ItemHodler) holder).gamedetailtime.setText(dayVideoInfoList.get(position).getTr().get(position).getTime());
            Glide.with(context).load(player1logopic).placeholder(R.drawable.biz_news_local_weather_bg_big).crossFade().into(((ItemHodler) holder).player1logo);
            Glide.with(context).load(player2logopic).placeholder(R.drawable.biz_news_local_weather_bg_big).crossFade().into(((ItemHodler) holder).player2logo);

        } else if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder progressHolder = (ProgressViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    progressHolder.textshow.setText("上拉加载更多...");
                    progressHolder.progressBar.setVisibility(View.INVISIBLE);
                    break;
                case LOADING_MORE:
                    progressHolder.textshow.setText("正在加载...");
                    progressHolder.progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar loadMore;
        private TextView textshow;

        public FootViewHolder(View itemView) {
            super(itemView);
            loadMore = (ProgressBar) itemView.findViewById(R.id.loadMore);
            textshow = (TextView) itemView.findViewById(R.id.textshow);
        }
    }

    public void addMoreItem(List<VideoBean.ResultVideo.DayVideoInfo> dayVideoInfos) {
        System.out.print(this.dayVideoInfoList.size() + "原有数据");
        System.out.print(dayVideoInfoList.size() + "新增有数据");
        this.dayVideoInfoList.addAll(dayVideoInfos);
        Lastposition = this.dayVideoInfoList.size();
        System.out.print(this.dayVideoInfoList.size() + "现有");
        notifyDataSetChanged();
    }

    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     * //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    class ItemHodler extends RecyclerView.ViewHolder {
        private TextView gamedaytime, player1name, gamedetailtime, gamestatues, player2name;
        private ImageView player1logo, player2logo;

        public ItemHodler(View itemView) {
            super(itemView);
            gamedaytime = (TextView) itemView.findViewById(R.id.gamedaytime);
            player1name = (TextView) itemView.findViewById(R.id.player1name);
            gamedetailtime = (TextView) itemView.findViewById(R.id.gamedetailtime);
            gamestatues = (TextView) itemView.findViewById(R.id.gamestatues);
            player2name = (TextView) itemView.findViewById(R.id.player2name);
            player1logo = (ImageView) itemView.findViewById(R.id.player1logo);
            player2logo = (ImageView) itemView.findViewById(R.id.player2logo);

        }
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView textshow;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loadMore);
            textshow = (TextView) itemView.findViewById(R.id.textshow);
        }
    }
}
