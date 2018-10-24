package com.example.p1024.mvp.model;

import android.app.Application;

import com.example.p1024.bean.NewSousuo;
import com.example.p1024.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.p1024.mvp.contract.SousuoContract;

import io.reactivex.Observable;


@ActivityScope
public class SousuoModel extends BaseModel implements SousuoContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SousuoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<NewSousuo> sousuo(String keywords) {
        Observable<NewSousuo> sousuo = mRepositoryManager.obtainRetrofitService(ApiService.class).sousuo(keywords);
        return sousuo;
    }
}