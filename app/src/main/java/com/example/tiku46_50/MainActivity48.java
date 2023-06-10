package com.example.tiku46_50;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity48 extends AppCompatActivity {

    private ImageView mMune38;
    private TextView mCX;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main48);
        initView();

        // 注册网络状态监听器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);

    }


    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                showAlertDialog("网络已连接");
            } else {
                showAlertDialog("网络已断开");
            }
        }
    };

    private void initView() {
        mMune38 = (ImageView) findViewById(R.id.mune38);
        mCX = (TextView) findViewById(R.id.CX);
        mTv = (TextView) findViewById(R.id.tv);
        mCX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
    public void getData(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        new H().sendResuilt("get_all_sense", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s=response.body().string();
                try {
                    JSONObject jsonObject1=new JSONObject(s);
                    String str=jsonObject1.getString("RESULT");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(str);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }





    // 弹出网络连接提示框
    private void showAlertDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v=getLayoutInflater().inflate(R.layout.dialog,null);
        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        TextView tv=v.findViewById(R.id.tv);
        TextView queidng=v.findViewById(R.id.queding);
        tv.setText(s);
        queidng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}