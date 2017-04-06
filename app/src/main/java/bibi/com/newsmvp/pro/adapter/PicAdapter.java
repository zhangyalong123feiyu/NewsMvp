package bibi.com.newsmvp.pro.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.activity.BigPicActivity;
import bibi.com.newsmvp.pro.activity.SocialH5Activity;
import bibi.com.newsmvp.pro.bean.ImageBean;
import bibi.com.newsmvp.pro.bean.SocailBean;

/**
 * Created by bibinet on 2017-1-6.
 */
public class PicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String IMG_NAME = "imagename";
    private final LayoutInflater inflater;
    private Context context;
    private List<ImageBean.Picinfo> picinfos = new ArrayList<>();

    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    public static int Lastposition;

    public PicAdapter(Context context, List<ImageBean.Picinfo> picinfos) {
        this.context = context;
        this.picinfos = picinfos;
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
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_pic, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            MyViewHolder itemHolder = (MyViewHolder) holder;
            final String picurl = picinfos.get(position).getPicUrl();
            //   Glide.with(context).load(picurl).placeholder(R.drawable.biz_news_local_weather_bg_big).crossFade().into(((MyViewHolder)holder).picimage);

            Picasso.with(context)
                    .load(picurl)
                    .placeholder(R.drawable.biz_news_local_weather_bg_big)
                    .into(((MyViewHolder) holder).picimage);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* myThread thread = new myThread(picurl);
                    thread.start();*/
                    // SaveBitmap(thread.bitmap);

                 /*   TransitionSet mtransitionset=new TransitionSet();//制定过度动画set
                    mtransitionset.addTransition(new ChangeBounds());//改变表框大小
                    mtransitionset.addTransition(new ChangeImageTransform());//图片移动，还可以是其他的，要什么效果自己添加
                    mtransitionset.setDuration(500);
                    ((Activity)context).getWindow().setEnterTransition(mtransitionset);//注意，下面是必须的
                    ((Activity)context). getWindow().setExitTransition(mtransitionset);
                    ((Activity)context).getWindow().setSharedElementEnterTransition(mtransitionset);
                    ((Activity)context).getWindow().setSharedElementExitTransition(mtransitionset);*/
                    ActivityOptionsCompat aop = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.itemView.findViewById(R.id.picimage)
                            , "123");
                    Intent intent = new Intent(context, BigPicActivity.class);
                    intent.putExtra("picurl", picurl);
                    ActivityCompat.startActivity((Activity) context, intent, aop.toBundle());
                    //context.startActivity(intent);
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

    class myThread extends Thread {
        private String url;
        private Bitmap bitmap;

        public myThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            super.run();
            bitmap = getBitmap(url);
        }
    }

    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    public static void SaveBitmap(Bitmap bitmap) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File saveFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "a", IMG_NAME);
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }

                FileOutputStream outStream = new FileOutputStream(saveFile);
                //第二个参数影响的是图片的质量，但是图片的宽度与高度是不会受影响滴
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return picinfos.size() > 0 ? picinfos.size() + 1 : 1;
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

    public void addMoreItem(List<ImageBean.Picinfo> newDatas) {
        System.out.print(this.picinfos.size() + "原有数据");
        System.out.print(newDatas.size() + "新增有数据");
        this.picinfos.addAll(newDatas);
        Lastposition = this.picinfos.size();
        System.out.print(this.picinfos.size() + "现有");
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView picimage;

        public MyViewHolder(View itemView) {
            super(itemView);
            picimage = (ImageView) itemView.findViewById(R.id.picimage);

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
