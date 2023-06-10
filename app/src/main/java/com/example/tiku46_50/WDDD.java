package com.example.tiku46_50;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WDDD extends AppCompatActivity {

    private ImageView mFanhui;
    private TextView mDzf;
    private TextView mYzf;
    private ExpandableListView mELV;
    List<DD>list=new ArrayList<>();
    List<DD>list1=new ArrayList<>();
    WDDD_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wddd);
        initView();
        setELV();

    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mDzf = (TextView) findViewById(R.id.dzf);
        mYzf = (TextView) findViewById(R.id.yzf);
        mELV = (ExpandableListView) findViewById(R.id.ELV);
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mDzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDzf.setBackgroundResource(R.drawable.x);
                mYzf.setBackgroundResource(R.drawable.z);
                setELV();
            }
        });
        mYzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDzf.setBackgroundResource(R.drawable.z);
                mYzf.setBackgroundResource(R.drawable.x);
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
        new  H().sendResuilt("get_bus_order", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s=response.body().string();
                try {
                    JSONObject jsonObject1=new JSONObject(s);
                    list=new Gson().fromJson(jsonObject1.optJSONArray("ROWS_DETAIL").toString(),
                            new TypeToken<List<DD>>(){}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter=new WDDD_Adapter(WDDD.this,list);
                            mELV.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void setELV(){
        DBmarager marager=new DBmarager(this);
        boolean b=marager.isExist("dingdans");
        if(b==false){

        }else {
           Cursor c= marager.queryDB("dingdans",null,null,null,null,null,null,null);
           list1=marager.sendee(c);
            adapter=new WDDD_Adapter(this,list1);
            mELV.setAdapter(adapter);
        }

    }
}