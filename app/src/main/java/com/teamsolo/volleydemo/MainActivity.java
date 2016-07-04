package com.teamsolo.volleydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.teamsolo.base.bean.implementation.CommonResponseBean;
import com.teamsolo.base.template.activity.BaseActivity;
import com.teamsolo.http_volley_okhttp_gson.VolleyManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FloatingActionButton fab;

    private ImageView imageView;

    private TextView textView, textView2;

    private VolleyManager volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        getBundle(getIntent());
        initViews();
        bindListeners();

        request();
    }

    @Override
    protected void getBundle(@NotNull Intent intent) {
        volley = VolleyManager.INSTANCE;
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    @Override
    protected void bindListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void request() {
        volley.imageLoaderRequest(
                imageView,
                "http://imgsrc.baidu.com/forum/w%3D580/sign=0a29c732ba99a9013b355b3e2d950a58/e789fb0735fae6cd6e90b0470db30f2442a70f15.jpg",
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher);

        volley.stringRequest(
                TAG,
                "http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Map<String, String> paras = new HashMap<>();
        volley.gsonPostRequest(
                TAG,
                paras,
                "http://wenxue.test.xweisoft.com/pc_secureproxy/parent/login",
                CommonResponseBean.class,
                new Response.Listener<CommonResponseBean>() {
                    @Override
                    public void onResponse(CommonResponseBean response) {
                        textView2.setText(response.getMessage());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        VolleyManager.INSTANCE.cancel(TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
