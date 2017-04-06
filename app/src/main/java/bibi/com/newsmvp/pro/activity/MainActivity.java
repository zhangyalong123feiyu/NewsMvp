package bibi.com.newsmvp.pro.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import bibi.com.newsmvp.R;
import bibi.com.newsmvp.pro.builder.GlideCircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.share)
    TextView share;
    @ViewInject(R.id.dayandnight)
    TextView dayandnight;
    @ViewInject(R.id.headphoto)
    private ImageView headphoto;
    @ViewInject(R.id.username)
    private TextView username;
    @ViewInject(R.id.news)
    private TextView news;
    @ViewInject(R.id.pics)
    private TextView pics;
    @ViewInject(R.id.weather)
    private TextView weather;
    @ViewInject(R.id.about)
    private TextView about;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.drawer)
    private DrawerLayout drawerLayout;
    private FragmentNews fragmentNews;
    private FragmentPic fragmentPic;
    private FragmentQuestion fragmentWeather;
    private FragmentAbout fragmentAbout;
    private TextView[] mTabs;
    private int index;
    private int currentTabIndex;
    private Fragment[] fragments;
    private long mPressedTime = 0;
    private SharedPreferences sharedPreferences;
    private int themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }*/
        inittheme();
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        x.view().inject(this);
        initview();
        setlistioner();

    }

    private void inittheme() {
        sharedPreferences = getSharedPreferences("themeapp", MODE_PRIVATE);
        themes = sharedPreferences.getInt("theme", R.style.DayAppTheme);
        setTheme(themes);
    }

    private void setlistioner() {

/*        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                news.setSelected(true);
                pics.setSelected(false);
                weather.setSelected(false);
                about.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragementcontainer,fragmentNews).commit();
            }
        });

        pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("美女图片");
                news.setSelected(false);
                pics.setSelected(true);
                weather.setSelected(false);
                about.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragementcontainer,fragmentPic).commit();
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("智能问答");
                news.setSelected(false);
                pics.setSelected(false);
                weather.setSelected(true);
                about.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragementcontainer,fragmentWeather).commit();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("关于");
                news.setSelected(false);
                pics.setSelected(false);
                weather.setSelected(false);
                about.setSelected(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragementcontainer,fragmentAbout).commit();
            }
        });*/
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "分享吧");
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
        dayandnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                themes = (themes == R.style.DayAppTheme) ? R.style.NightAppTheme : R.style.DayAppTheme;
                MainActivity.this.recreate();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", themes);
                editor.commit();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // outState.putInt("theme", theme);
    }

    public void onTabClicked(View view) {
        drawerLayout.closeDrawers();
        switch (view.getId()) {
            case R.id.news:
                index = 0;
                break;
            case R.id.pics:
                index = 1;
                break;
            case R.id.weather:
                index = 2;
                break;
            case R.id.about:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragementcontainer, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    private void initview() {
        Glide.with(this)
                .load(R.drawable.protrait)
                .transform(new GlideCircleTransform(this))
                .into(headphoto);
        getSupportActionBar();


        toolbar.setTitle("新闻");

        fragmentNews = new FragmentNews();
        fragmentPic = new FragmentPic();
        fragmentWeather = new FragmentQuestion();
        fragmentAbout = new FragmentAbout();
        fragments = new Fragment[]{fragmentNews, fragmentPic, fragmentWeather, fragmentAbout};
        //   news.setSelected(true);

        // getSupportFragmentManager().beginTransaction().add(R.id.fragementcontainer,fragmentNews).show(fragmentNews).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragementcontainer, fragmentNews).show(fragmentNews).
                add(R.id.fragementcontainer, fragmentPic).hide(fragmentPic).add(R.id.fragementcontainer, fragmentWeather).hide(fragmentWeather)
                .add(R.id.fragementcontainer, fragmentAbout).hide(fragmentAbout).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, zhuye_fragement).show(zhuye_fragement).commit();

        mTabs = new TextView[4];
        mTabs[0] = (TextView) findViewById(R.id.news);
        mTabs[1] = (TextView) findViewById(R.id.pics);
        mTabs[2] = (TextView) findViewById(R.id.weather);
        mTabs[3] = (TextView) findViewById(R.id.about);
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);

    }

    //双击退出程序
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
