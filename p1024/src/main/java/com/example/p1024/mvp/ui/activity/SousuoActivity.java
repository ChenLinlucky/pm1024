package com.example.p1024.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p1024.R;
import com.example.p1024.bean.NewSousuo;
import com.example.p1024.di.component.DaggerSousuoComponent;
import com.example.p1024.di.module.SousuoModule;
import com.example.p1024.mvp.contract.SousuoContract;
import com.example.p1024.mvp.presenter.SousuoPresenter;
import com.example.p1024.mvp.ui.activity.adapter.SouAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SousuoActivity extends BaseActivity<SousuoPresenter> implements SousuoContract.View {

    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.btn_sou)
    Button btnSou;
    @BindView(R.id.sou_recyview)
    RecyclerView souRecyview;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSousuoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sousuoModule(new SousuoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_sousuo; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //搜索
    @Override
    public void datasou(NewSousuo newSousuo) {
        List<NewSousuo.DataBean> data = newSousuo.getData();
        //Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(SousuoActivity.this, LinearLayoutManager.VERTICAL, false);
        souRecyview.setLayoutManager(manager);
        SouAdapter adapter = new SouAdapter(SousuoActivity.this,data);
        souRecyview.setAdapter(adapter);
    }

    @OnClick(R.id.btn_sou)
    public void onViewClicked() {
        String trim = editSou.getText().toString().trim();
        mPresenter.sousuo(trim);
    }

}
