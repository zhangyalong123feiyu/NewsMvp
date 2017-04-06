package bibi.com.newsmvp.pro.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.activity.H5Activity;
import bibi.com.newsmvp.pro.bean.TopNewsBean;

/**
 * Created by bibinet on 2017-1-5.
 */
public class TopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TopNewsBean.NewsResult.NewInfo> infos = new ArrayList<>();


    public TopListAdapter(List<TopNewsBean.NewsResult.NewInfo> infos, Context context) {
        this.infos = infos;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        TopNewsBean.NewsResult.NewInfo info = infos.get(position);
        if (TextUtils.isEmpty(info.getThumbnail_pic_s02())) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.newsitemone, parent, false);
            return new MyholderSingle(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.newsitemmore, parent, false);
            return new Myholder(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        String datetiem = infos.get(position).getDate();
        String newstitle = infos.get(position).getTitle();
        String url1 = infos.get(position).getThumbnail_pic_s();
        if (getItemViewType(position) == 1) {
            ((MyholderSingle) holder).titlenews.setText(newstitle);
            ((MyholderSingle) holder).newstime.setText(datetiem);
            Glide.with(context).load(url1).into(((MyholderSingle) holder).iv1);
        } else if (getItemViewType(position) == 2) {
            ((Myholder) holder).titlenews.setText(newstitle);
            ((Myholder) holder).newstime.setText(datetiem);


            String url2 = infos.get(position).getThumbnail_pic_s02();
            String url3 = infos.get(position).getThumbnail_pic_s03();

            if (!TextUtils.isEmpty(url1)) {
                Glide.with(context).load(url1).into(((Myholder) holder).iv1);
            }

            if (!TextUtils.isEmpty(url2)) {
                Glide.with(context).load(url2).into(((Myholder) holder).iv2);
            }

            if (!TextUtils.isEmpty(url3)) {
                Glide.with(context).load(url1).into(((Myholder) holder).iv3);
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, H5Activity.class);
                intent.putExtra("newdetailurl", infos.get(position).getUrl());
                intent.putExtra("newdetailtitle", infos.get(position).getTitle());
                intent.putExtra("newdetailimageurl", infos.get(position).getThumbnail_pic_s());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class Myholder extends RecyclerView.ViewHolder {
        private TextView titlenews, newstime;
        private ImageView iv1, iv2, iv3;

        public Myholder(View itemView) {
            super(itemView);
            titlenews = (TextView) itemView.findViewById(R.id.titlenews);
            newstime = (TextView) itemView.findViewById(R.id.titletime);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            iv2 = (ImageView) itemView.findViewById(R.id.iv2);
            iv3 = (ImageView) itemView.findViewById(R.id.iv3);
        }
    }

    class MyholderSingle extends RecyclerView.ViewHolder {
        private TextView titlenews, newstime;
        private ImageView iv1;

        public MyholderSingle(View itemView) {
            super(itemView);
            titlenews = (TextView) itemView.findViewById(R.id.newstitle);
            newstime = (TextView) itemView.findViewById(R.id.newstime);
            iv1 = (ImageView) itemView.findViewById(R.id.ivone);
        }
    }
}
