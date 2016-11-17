package com.like.ling.http.listener;

/**
 * 响应接口
 */
public interface IResponseListener {

    /**
     * @param action  用来区别多重请求
     * @param jsonStr 返回的值
     */
    public void onNetworkResponsed(int action, String jsonStr);
}
