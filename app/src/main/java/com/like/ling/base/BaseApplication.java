package com.like.ling.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * @author ling
 * @version 1.0
 * 2016/11/17 13:09
 */
public class BaseApplication extends Application {
    private static RequestQueue mQueue;
    public static RequestQueue getRequestQueue(){
        return mQueue;
    }
    @Override
    public void onCreate() {
        mQueue= Volley.newRequestQueue(this);
    }
}
