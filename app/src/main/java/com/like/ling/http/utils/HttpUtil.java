package com.like.ling.http.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.like.ling.R;
import com.like.ling.base.BaseApplication;
import com.like.ling.http.listener.IResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ling
 * @version 1.0
 *          2016/11/17 10:38
 */
public class HttpUtil {

    private volatile static HttpUtil sHttpUtil;

    private HttpUtil() {
    }

    public static HttpUtil getHttpUtil() {
        if (sHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (sHttpUtil == null) {
                    sHttpUtil = new HttpUtil();
                }
            }
        }
        return sHttpUtil;
    }


    private IResponseListener mListener;

    public void setListener(IResponseListener listener) {
        mListener = listener;
    }

    public void doGet(final int action, String urlPath) {
        //传递一二Url对象,String 类型的json语句
        StringRequest request = new StringRequest(urlPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (mListener != null) {
                    mListener.onNetworkResponsed(action, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse " + error.getLocalizedMessage());
            }
        });
        //一般 一个应用多个请求--->请求队列里面去
        BaseApplication.getRequestQueue().add(request);
    }

    public void doPost(final int action, String urlPath, final HashMap<String, String> paramsMap) {
        StringRequest request = new StringRequest(Request.Method.POST, urlPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "Post onResponse " + response);
                if (mListener != null) {
                    mListener.onNetworkResponsed(action, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse " + error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paramsMap;
            }
        };
        BaseApplication.getRequestQueue().add(request);
    }

    public void imageRequest(String urlPath, final ImageView imageView) {
        ImageRequest request = new ImageRequest(urlPath, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        BaseApplication.getRequestQueue().add(request);
    }

    public void imageLoader(String urlPath, final ImageView imageView) {
        ImageLoader imageLoader = new ImageLoader(BaseApplication.getRequestQueue(), new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(urlPath, imageListener);

    }
}
