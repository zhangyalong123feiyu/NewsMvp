package bibi.com.newsmvp.pro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.bean.ChatBean;
import bibi.com.newsmvp.pro.builder.GlideCircleTransform;

/**
 * Created by bibinet on 2017-1-7.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatBean> datas;
    // private List<String> mecontents;
    private Context context;

    public ChatAdapter(List<ChatBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_qestionme, parent, false);
            return new MeContentHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_questionrobot, parent, false);
            return new RoobtHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((MeContentHolder) holder).content.setText(datas.get(position).getResult().getText());
            Glide.with(context).load(R.drawable.protrait).transform(new GlideCircleTransform(context)).into(((MeContentHolder) holder).mephoto);
        }
        if (getItemViewType(position) == 2) {
            ((RoobtHolder) holder).content.setText(datas.get(position).getResult().getText());
            Glide.with(context).load(R.mipmap.ic_launcher).transform(new GlideCircleTransform(context)).into(((RoobtHolder) holder).roobtphoto);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MeContentHolder extends RecyclerView.ViewHolder {
        private ImageView mephoto;
        private TextView content;

        public MeContentHolder(View itemView) {
            super(itemView);
            mephoto = (ImageView) itemView.findViewById(R.id.mephoto);
            content = (TextView) itemView.findViewById(R.id.textcontent);
        }
    }

    class RoobtHolder extends RecyclerView.ViewHolder {
        private ImageView roobtphoto;
        private TextView content;

        public RoobtHolder(View itemView) {
            super(itemView);
            roobtphoto = (ImageView) itemView.findViewById(R.id.roobtphoto);
            content = (TextView) itemView.findViewById(R.id.textcontent);
        }
    }
}
