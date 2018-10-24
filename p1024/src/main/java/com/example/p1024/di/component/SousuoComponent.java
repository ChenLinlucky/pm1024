package com.example.p1024.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.p1024.di.module.SousuoModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.p1024.mvp.ui.activity.SousuoActivity;

@ActivityScope
@Component(modules = SousuoModule.class, dependencies = AppComponent.class)
public interface SousuoComponent {
    void inject(SousuoActivity activity);
}