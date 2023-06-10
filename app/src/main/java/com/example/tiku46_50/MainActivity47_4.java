package com.example.tiku46_50;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity47_4 extends AppCompatActivity {

    private ImageView mFanhui;
    private TextView mXianlu;
    private EditText mName;
    private EditText mTel;
    private Spinner mScdd;
    private Button mBT3;
    private String xianlu;
    private String riqi;
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    private Spinner mXcdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main474);
        initView();
        Intent intent = getIntent();
        xianlu = intent.getStringExtra("xianlu");
        riqi = intent.getStringExtra("riqi");
        mXianlu.setText(xianlu);
        list1.add("光谷金融街");
        list1.add("戎军南路");
        list1.add("长河公园");
        list1.add("南湖商厦");

        list2.add("德州职业");
        list2.add("大学东路");
        list2.add("奥德乐广场");
        list2.add("德州站");


        if (xianlu.equals("光谷金融街——南湖商厦")) {
            ArrayAdapter adapter = new ArrayAdapter(MainActivity47_4.this, R.layout.spinner_item, R.id.t, list1);
            mScdd.setAdapter(adapter);
            mXcdd.setAdapter(adapter);
        } else {
            ArrayAdapter adapter = new ArrayAdapter(MainActivity47_4.this, R.layout.spinner_item, R.id.t, list2);
            mScdd.setAdapter(adapter);
            mXcdd.setAdapter(adapter);
        }
    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mXianlu = (TextView) findViewById(R.id.xianlu);
        mName = (EditText) findViewById(R.id.name);
        mTel = (EditText) findViewById(R.id.tel);
        mScdd = (Spinner) findViewById(R.id.scdd);
        mXcdd = (Spinner) findViewById(R.id.xcdd);
        mBT3 = (Button) findViewById(R.id.BT3);
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity47_4.this, MainActivity47_5.class);
                intent.putExtra("xianlu", mXianlu.getText().toString());
                intent.putExtra("tel", mTel.getText().toString());
                intent.putExtra("scdd", mScdd.getSelectedItem().toString());
                intent.putExtra("xcdd", mXcdd.getSelectedItem().toString());
                intent.putExtra("name", mName.getText().toString());
                intent.putExtra("time", riqi);
                startActivity(intent);
            }
        });

    }
}