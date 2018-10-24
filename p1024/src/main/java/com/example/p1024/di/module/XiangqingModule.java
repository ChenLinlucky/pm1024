package com.example.p1024.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.p1024.mvp.contract.XiangqingContract;
import com.example.p1024.mvp.model.XiangqingModel;


@Module
public class XiangqingModule {
    private XiangqingContract.View view;

    /**
     * 构建XiangqingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public XiangqingModule(XiangqingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    XiangqingContract.View provideXiangqingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    XiangqingContract.Model provideXiangqingModel(XiangqingModel model) {
        return model;
    }
}