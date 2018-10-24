package com.example.p1024.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.p1024.R;
import com.example.p1024.bean.NewsJiugongge;
import com.example.p1024.bean.NewsLunbo;
import com.example.p1024.bean.NewsTuijian;
import com.example.p1024.di.component.DaggerMainComponent;
import com.example.p1024.di.module.MainModule;
import com.example.p1024.mvp.contract.MainContract;
import com.example.p1024.mvp.presenter.MainPresenter;
import com.example.p1024.mvp.ui.activity.adapter.JiugonggeAdapter;
import com.example.p1024.mvp.ui.activity.adapter.TuijianAdapter;
import com.example.p1024.mvp.ui.activity.widgh.NoticeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.flybanner)
    FlyBanner flybanner;
    @BindView(R.id.recy_jiugongge)
    RecyclerView recyJiugongge;
    @BindView(R.id.not_view)
    NoticeView notView;
    @BindView(R.id.recy_tui)
    RecyclerView recyTui;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.sou)
    Button sou;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.lunbo();
        mPresenter.jiugongge();
        mPresenter.tuijian();
        //跑马灯
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        notView.addNotice(notices);
        notView.startFlipping();
        //刷新
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(2000);
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(2000);
            }
        });
        //设置 Header 为 贝塞尔雷达 样式
        smart.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲 样式
        smart.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    //轮播
    @Override
    public void datalunbo(NewsLunbo newsLunbo) {
        List<NewsLunbo.DataBean> data = newsLunbo.getData();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            list.add(icon);
        }
        flybanner.setImagesUrl(list);
    }

    //9宫格
    @Override
    public void datajiugongge(NewsJiugongge newsJiugongge) {
        List<NewsJiugongge.DataBean> data = newsJiugongge.getData();
        // Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        recyJiugongge.setLayoutManager(manager);
        JiugonggeAdapter adapter = new JiugonggeAdapter(MainActivity.this, data);
        recyJiugongge.setAdapter(adapter);
    }

    //商品推荐，，点击跳转商品详情页
    @Override
    public void datatuijian(NewsTuijian newsTuijian) {
        List<NewsTuijian.DataBean.TuijianBean.ListBeanX> list = newsTuijian.getData().getTuijian().getList();
        //Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recyTui.setLayoutManager(manager);
        TuijianAdapter adapter = new TuijianAdapter(MainActivity.this, list);
        //点击进入详情页面
        adapter.setOnitemclick(new TuijianAdapter.onitemclick() {
            @Override
            public void onclick(int position) {
                int pid = newsTuijian.getData().getTuijian().getList().get(position).getPid();
                Intent intent = new Intent(MainActivity.this, XiangqingActivity.class);
                startActivity(intent);
            }
        });
        recyTui.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sou)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, SousuoActivity.class);
        startActivity(intent);
    }
}
