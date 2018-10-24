package com.example.p1024.mvp.model;

import android.app.Application;

import com.example.p1024.bean.NewsXiangqing;
import com.example.p1024.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.p1024.mvp.contract.XiangqingContract;

import io.reactivex.Observable;


@ActivityScope
public class XiangqingModel extends BaseModel implements XiangqingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public XiangqingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<NewsXiangqing> requestxiangqing(int id) {
        Observable<NewsXiangqing> xiangqing = mRepositoryManager.obtainRetrofitService(ApiService.class).xiangqing(id);
        return xiangqing;
    }
}