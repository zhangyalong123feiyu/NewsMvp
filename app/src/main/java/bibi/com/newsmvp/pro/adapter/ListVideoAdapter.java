package bibi.com.newsmvp.pro.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.bean.VideoModel;

/**
 * Created by shuyu on 2016/11/11.
 */

public class ListVideoAdapter extends BaseAdapter {

    public final static String TAG = "TT2";

    private List<VideoModel> list = new ArrayList<>();
    private LayoutInflater inflater = null;
    private Context context;

    private ViewGroup rootView;
    private OrientationUtils orientationUtils;

    private boolean isFullVideo;

    private ListVideoUtil listVideoUtil;
    private List<IDanmakuItem> danmuklist = new ArrayList<>();

    public ListVideoAdapter(Context context, ListVideoUtil listVideoUtil) {
        super();
        this.context = context;
        this.listVideoUtil = listVideoUtil;
        danmuklist.add(new DanmakuItem(context, "zhangyalong", 100));
        danmuklist.add(new DanmakuItem(context, "fanchunyan", 100));
        danmuklist.add(new DanmakuItem(context, "号店", 100));
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < 40; i++) {
            list.add(new VideoModel());
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_video_item, null);
            holder.videoContainer = (FrameLayout) convertView.findViewById(R.id.list_item_container);
            holder.playerBtn = (ImageView) convertView.findViewById(R.id.list_item_btn);
            holder.danmakuView = (DanmakuView) convertView.findViewById(R.id.danmakuview);
            holder.imageView = new ImageView(context);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //增加封面

        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageView.setImageResource(R.mipmap.xxx1);
        // Glide.with(context).load("http://baobab.wdjcdn.com/14564977406580.mp4").asGif().into(holder.imageView);
        holder.danmakuView.addItem(danmuklist, false);
        holder.danmakuView.show();
        listVideoUtil.addVideoPlayer(position, holder.imageView, TAG, holder.videoContainer, holder.playerBtn);

        holder.playerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                //listVideoUtil.setLoop(true);
                listVideoUtil.setPlayPositionAndTag(position, TAG);
                final String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
                //listVideoUtil.setCachePath(new File(FileUtils.getPath()));
                listVideoUtil.startPlay(url);
            }
        });
        return convertView;
    }

    class ViewHolder {
        FrameLayout videoContainer;
        ImageView playerBtn;
        ImageView imageView;
        DanmakuView danmakuView;
    }

    public void setRootView(ViewGroup rootView) {
        this.rootView = rootView;
    }

}
