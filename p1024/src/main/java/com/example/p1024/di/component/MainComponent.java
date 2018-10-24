package com.example.p1024.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.p1024.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.p1024.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}