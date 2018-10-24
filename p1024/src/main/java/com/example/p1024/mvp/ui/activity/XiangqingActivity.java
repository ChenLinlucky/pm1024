package com.example.p1024.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.p1024.bean.NewsXiangqing;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.p1024.di.component.DaggerXiangqingComponent;
import com.example.p1024.di.module.XiangqingModule;
import com.example.p1024.mvp.contract.XiangqingContract;
import com.example.p1024.mvp.presenter.XiangqingPresenter;

import com.example.p1024.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class XiangqingActivity extends BaseActivity<XiangqingPresenter> implements XiangqingContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerXiangqingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .xiangqingModule(new XiangqingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_xiangqing; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.xiangqing(1);
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

    @Override
    public void dataxiangqing(NewsXiangqing newsXiangqing) {
        NewsXiangqing.DataBean data = newsXiangqing.getData();
        TextView name = findViewById(R.id.xiang_name);
        TextView price = findViewById(R.id.xiang_price);
        SimpleDraweeView simp = findViewById(R.id.xiang_simp);
        String[] split = data.getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        simp.setImageURI(uri);
        name.setText(data.getTitle());
        price.setText("价格为："+data.getPrice()+"元");
    }
}
