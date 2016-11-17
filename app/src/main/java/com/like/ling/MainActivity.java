package com.like.ling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "nihao", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "yoyo", Toast.LENGTH_SHORT).show();

        //我再来两个吐司好了
        Toast.makeText(MainActivity.this, "吐司", Toast.LENGTH_SHORT).show();
    }
}
