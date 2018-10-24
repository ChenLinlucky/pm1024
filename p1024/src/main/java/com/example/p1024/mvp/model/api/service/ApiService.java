package com.example.p1024.mvp.model.api.service;

import com.example.p1024.bean.NewSousuo;
import com.example.p1024.bean.NewsJiugongge;
import com.example.p1024.bean.NewsLunbo;
import com.example.p1024.bean.NewsTuijian;
import com.example.p1024.bean.NewsXiangqing;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //轮播
    @GET("ad/getAd")
    Observable<NewsLunbo> lunbo();
    //九宫格
    @GET("product/getCatagory")
    Observable<NewsJiugongge> jiugongge();
    //商品推荐
    @GET("home/getHome?")
    Observable<NewsTuijian> tuijian();
    //商品详情
    @GET("product/getProductDetail?")
    Observable<NewsXiangqing> xiangqing(@Query("pid")int id);
    //关键字搜索
    @GET("product/searchProducts?")
    Observable<NewSousuo> sousuo(@Query("keywords")String keywords);
}
