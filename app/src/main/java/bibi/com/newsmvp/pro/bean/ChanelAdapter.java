package bibi.com.newsmvp.pro.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.application.MyApplication;

/**
 * Created by bibinet on 2017-1-16.
 */
public class ChanelAdapter extends RecyclerView.Adapter<ChanelAdapter.ChanelHolder> {
    private Context context;
    private List<ChannelTable> chanels = new ArrayList<>();
    private ChanelHolder chanelHolder;

    public ChanelAdapter(Context context, List<ChannelTable> chanels) {
        this.context = context;
        this.chanels = chanels;
    }

    @Override
    public ChanelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chanel, parent, false);
        chanelHolder = new ChanelHolder(view);
        return chanelHolder;
    }

    @Override
    public void onBindViewHolder(ChanelHolder holder, int position) {
        holder.chanelname.setText(chanels.get(position).getChannelname());
        holder.chanelname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropData();
            }
        });
    }

    private void dropData() {
        DbManager db = x.getDb(MyApplication.getDaoConfig());

    }

    @Override
    public int getItemCount() {
        return chanels.size();
    }

    public void setvisible() {
        chanelHolder.imageView.setVisibility(View.VISIBLE);
    }

    public void setgone() {
        chanelHolder.imageView.setVisibility(View.GONE);
    }

    class ChanelHolder extends RecyclerView.ViewHolder {
        private TextView chanelname;
        private ImageView imageView;

        public ChanelHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.deleteimage);
            chanelname = (TextView) itemView.findViewById(R.id.chanelname);
        }
    }
}
