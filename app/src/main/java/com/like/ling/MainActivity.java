package com.like.ling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.like.ling.http.config.IdiyMessage;
import com.like.ling.http.listener.IResponseListener;
import com.like.ling.http.utils.HttpUtil;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IResponseListener {

    private Button mDoget;
    private Button mDopost;
    private HttpUtil mHttpUtil;
    private Button mImageloaderBtn;
    private Button mPostrequestBtn;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果其他地方是有应用那么就需要全局applicaton
        initView();
        mHttpUtil = HttpUtil.getHttpUtil();
        mHttpUtil.setListener(this);
    }

    private void initView() {
        mDoget = (Button) findViewById(R.id.doget);
        mDopost = (Button) findViewById(R.id.dopost);
        mDoget.setOnClickListener(this);
        mDopost.setOnClickListener(this);
        mImageloaderBtn = (Button) findViewById(R.id.imageloader_btn);
        mImageloaderBtn.setOnClickListener(this);
        mPostrequestBtn = (Button) findViewById(R.id.postrequest_btn);
        mPostrequestBtn.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doget:
                getRequest();
                break;
            case R.id.dopost:
                postRequest();
                break;
            case R.id.imageloader_btn:
                imageLoader();
                break;
            case R.id.postrequest_btn:
                imageRequest();
                break;
        }
    }

    private void imageLoader() {

        String urlPath = "http://img2.everychina.com/img/d1/d7/144a8de7c562219d32d640d3f1ea-600x400c1-c847/teddy_bear_toy_doll.jpg";
        mHttpUtil.imageLoader(urlPath,mImage);

    }

    private void imageRequest() {
        String urlPath = "http://img2.everychina.com/img/d1/d7/144a8de7c562219d32d640d3f1ea-600x400c1-c847/teddy_bear_toy_doll.jpg";
        mHttpUtil.imageRequest(urlPath,mImage);
    }

    private void postRequest() {

        String urlPath = "http://mall.520it.com/login";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("username", "2");
        paramsMap.put("pwd", "123456");
        mHttpUtil.doPost(IdiyMessage.JDLODING_ACTION, urlPath, paramsMap);

    }

    private void getRequest() {
        mHttpUtil.doGet(IdiyMessage.BAIDU_ACTION, "https://www.baidu.com");
    }

    @Override
    public void onNetworkResponsed(int action, String jsonStr) {
        switch (action) {
            case IdiyMessage.BAIDU_ACTION:
                //                Log.i("TAG", "baidu  " + jsonStr);
                Toast.makeText(getApplicationContext(), jsonStr, Toast.LENGTH_SHORT).show();
                break;
            case IdiyMessage.JDLODING_ACTION:
                //                Log.i("TAG", "JD登录 " + jsonStr);
                Toast.makeText(getApplicationContext(), jsonStr, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
