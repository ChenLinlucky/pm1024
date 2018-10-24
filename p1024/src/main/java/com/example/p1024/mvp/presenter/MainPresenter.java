package com.example.p1024.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.p1024.bean.NewsJiugongge;
import com.example.p1024.bean.NewsLunbo;
import com.example.p1024.bean.NewsTuijian;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.example.p1024.mvp.contract.MainContract;


@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    @SuppressLint("CheckResult")
    public void lunbo(){
        mModel.requestlunbo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsLunbo>() {
                    @Override
                    public void accept(NewsLunbo newsLunbo) throws Exception {
                        mRootView.datalunbo(newsLunbo);
                    }
                });
    }
    @SuppressLint("CheckResult")
    public void jiugongge(){
        Observable<NewsJiugongge> requestjiugongge = mModel.requestjiugongge();
        requestjiugongge.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsJiugongge>() {
                    @Override
                    public void accept(NewsJiugongge newsJiugongge) throws Exception {
                        mRootView.datajiugongge(newsJiugongge);
                    }
                });
    }
    @SuppressLint("CheckResult")
    public void tuijian(){
        Observable<NewsTuijian> requesttuijian = mModel.requesttuijian();
        requesttuijian.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsTuijian>() {
                    @Override
                    public void accept(NewsTuijian newsTuijian) throws Exception {
                        mRootView.datatuijian(newsTuijian);
                    }
                });
    }
}
