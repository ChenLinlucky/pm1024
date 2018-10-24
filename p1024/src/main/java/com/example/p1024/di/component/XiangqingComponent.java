package com.example.p1024.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.p1024.di.module.XiangqingModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.p1024.mvp.ui.activity.XiangqingActivity;

@ActivityScope
@Component(modules = XiangqingModule.class, dependencies = AppComponent.class)
public interface XiangqingComponent {
    void inject(XiangqingActivity activity);
}