package bibi.com.newsmvp.pro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.activity.SocialH5Activity;
import bibi.com.newsmvp.pro.bean.SocailBean;
import bibi.com.newsmvp.pro.builder.OnLoadMoreListener;

/**
 * Created by bibinet on 2017-1-6.
 */
public class SocailFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    private List<SocailBean.SocailInfo> socailInfos;

    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    public static int Lastposition;

    public SocailFooterAdapter(Context context, List<SocailBean.SocailInfo> socailInfos) {
        this.context = context;
        this.socailInfos = socailInfos;
        Lastposition = socailInfos.size();
        inflater = LayoutInflater.from(context);
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
        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.newsitemone, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ItemHolder itemViewHolder = new ItemHolder(view);
            return itemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = inflater.inflate(R.layout.progressbar_item, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ProgressViewHolder footViewHolder = new ProgressViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.newstitle.setText(socailInfos.get(position).getTitle());
            itemHolder.newstime.setText(socailInfos.get(position).getCtime());
            Glide.with(context).load(socailInfos.get(position).getPicUrl()).into(itemHolder.iv1);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SocialH5Activity.class);
                    intent.putExtra("socialdetail", socailInfos.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
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

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar loadMore;
        private TextView textshow;

        public FootViewHolder(View itemView) {
            super(itemView);
            loadMore = (ProgressBar) itemView.findViewById(R.id.loadMore);
            textshow = (TextView) itemView.findViewById(R.id.textshow);
        }
    }

    public void addMoreItem(List<SocailBean.SocailInfo> newDatas) {
        System.out.print(this.socailInfos.size() + "原有数据");
        System.out.print(newDatas.size() + "新增有数据");
        this.socailInfos.addAll(newDatas);
        Lastposition = this.socailInfos.size();
        System.out.print(this.socailInfos.size() + "现有");
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

    @Override
    public int getItemCount() {
        return socailInfos.size() > 0 ? socailInfos.size() + 1 : 1;
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

    class ItemHolder extends RecyclerView.ViewHolder {
        public TextView newstitle, newstime;
        public ImageView iv1;

        public ItemHolder(View itemView) {
            super(itemView);
            newstitle = (TextView) itemView.findViewById(R.id.newstitle);
            newstime = (TextView) itemView.findViewById(R.id.newstime);
            iv1 = (ImageView) itemView.findViewById(R.id.ivone);
        }
    }
}
