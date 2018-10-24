package com.example.p1024.mvp.model;

import android.app.Application;

import com.example.p1024.bean.NewsJiugongge;
import com.example.p1024.bean.NewsLunbo;
import com.example.p1024.bean.NewsTuijian;
import com.example.p1024.mvp.model.api.service.ApiService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.p1024.mvp.contract.MainContract;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<NewsLunbo> requestlunbo() {
        Observable<NewsLunbo> lunbo = mRepositoryManager.obtainRetrofitService(ApiService.class).lunbo();
        return lunbo;
    }

    @Override
    public Observable<NewsJiugongge> requestjiugongge() {
        Observable<NewsJiugongge> jiugongge = mRepositoryManager.obtainRetrofitService(ApiService.class).jiugongge();
        return jiugongge;
    }

    @Override
    public Observable<NewsTuijian> requesttuijian() {
        Observable<NewsTuijian> tuijian = mRepositoryManager.obtainRetrofitService(ApiService.class).tuijian();
        return tuijian;
    }
}