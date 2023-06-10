package com.example.tiku46_50;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity47_5 extends AppCompatActivity {

    private ImageView mFanhui;
    private TextView mXianlu;
    private TextView mName;
    private TextView mTel;
    private TextView mDd;
    private TextView mYmd;
    private Button mBT3;
    private TextView mXcdd;

    List<DD>list=new ArrayList<>();
    String money=MainActivity47_2.getMoney();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main475);
        initView();
        Intent intent = getIntent();
        mDd.setText(intent.getStringExtra("scdd"));
        mXcdd.setText(intent.getStringExtra("xcdd"));
        mXianlu.setText(intent.getStringExtra("xianlu"));
        mName.setText(intent.getStringExtra("name"));
        mYmd.setText(intent.getStringExtra("time"));
        mTel.setText(intent.getStringExtra("tel"));

    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mXianlu = (TextView) findViewById(R.id.xianlu);
        mName = (TextView) findViewById(R.id.name);
        mTel = (TextView) findViewById(R.id.tel);
        mDd = (TextView) findViewById(R.id.dd);
        mYmd = (TextView) findViewById(R.id.ymd);
        mBT3 = (Button) findViewById(R.id.BT3);
        mXcdd = (TextView) findViewById(R.id.xcdd);
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBmarager dBmarager=new DBmarager(MainActivity47_5.this);
                boolean b= dBmarager.isExist("dingdans");
                if(b==false){
                    String sql = "create table dingdans (" +
                            "id integer primary key autoincrement," +
                            "                           number varchar," +
                            "                           money integer," +
                            "                           xianlu varchar," +
                            "                           time varchar);";
                    dBmarager.createtable(sql);
                }

                ContentValues cv=new ContentValues();
                cv.put("number","未支付");
                cv.put("money",money);
                cv.put("xianlu",mXianlu.getText().toString());
                cv.put("time",mYmd.getText().toString());
                dBmarager.insertDB("dingdans",cv);

            }
        });

    }
}