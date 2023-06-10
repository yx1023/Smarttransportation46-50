package com.example.tiku46_50;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity49 extends AppCompatActivity {

    private WebView mWv;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main49);
        initView();
        mWv.getSettings().setJavaScriptEnabled(true);

        mWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });


        mWv.loadUrl("file:///android_asset/Web.html");



    }

    private void initView() {
        mWv = (WebView) findViewById(R.id.wv);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}