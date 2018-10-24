package com.example.p1024.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.p1024.bean.NewSousuo;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.example.p1024.mvp.contract.SousuoContract;


@ActivityScope
public class SousuoPresenter extends BasePresenter<SousuoContract.Model, SousuoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SousuoPresenter(SousuoContract.Model model, SousuoContract.View rootView) {
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
    public void sousuo(String keywords){
        mModel.sousuo(keywords).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewSousuo>() {
                    @Override
                    public void accept(NewSousuo newSousuo) throws Exception {
                        mRootView.datasou(newSousuo);
                    }
                });
    }
}
