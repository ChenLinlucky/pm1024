package com.example.p1024.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.p1024.mvp.contract.SousuoContract;
import com.example.p1024.mvp.model.SousuoModel;


@Module
public class SousuoModule {
    private SousuoContract.View view;

    /**
     * 构建SousuoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SousuoModule(SousuoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SousuoContract.View provideSousuoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SousuoContract.Model provideSousuoModel(SousuoModel model) {
        return model;
    }
}