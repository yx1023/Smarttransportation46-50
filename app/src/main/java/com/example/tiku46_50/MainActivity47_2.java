package com.example.tiku46_50;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity47_2 extends AppCompatActivity {

    private ImageView mFanhui;
    private ImageView mDitu;
    private TextView mXianlu;
    private TextView mMoney;
    private TextView mKm;
    private Button mBT1;
    private String xianlu;
    static String money;

    public static String getMoney() {
        return money;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main472);
        initView();
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        xianlu = intent.getStringExtra("xianlu");
        System.out.println(xianlu +"----------------");
        if(id==0){
            mDitu.setImageResource(R.drawable.ditu);
            mKm.setText("里程：20.0km");
            mMoney.setText("票价：￥8.0");
            money="8";
            mXianlu.setText("光顾金融界——南湖商厦");
        }else {
            mDitu.setImageResource(R.drawable.ditu2);
            mKm.setText("里程：24.0km");
            mMoney.setText("票价：￥9.0");
            money="9";
            mXianlu.setText("德州职业——德州站");
        }


    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mDitu = (ImageView) findViewById(R.id.ditu);
        mXianlu = (TextView) findViewById(R.id.xianlu);
        mMoney = (TextView) findViewById(R.id.money);
        mKm = (TextView) findViewById(R.id.km);
        mBT1 = (Button) findViewById(R.id.BT1);
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity47_2.this,MainActivity47_3.class);
                intent.putExtra("xianlu",xianlu);
                startActivity(intent);
            }
        });
    }
}