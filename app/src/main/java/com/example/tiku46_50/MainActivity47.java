package com.example.tiku46_50;

import android.content.Intent;
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

public class MainActivity47 extends AppCompatActivity {

    private ImageView mMune38;
    private TextView mDd;
    private ExpandableListView mELV;

    List<Car>list=new ArrayList<>();
    ELV_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main47);
        initView();
        getData();
    }

    private void initView() {
        mMune38 = (ImageView) findViewById(R.id.mune38);
        mDd = (TextView) findViewById(R.id.dd);
        mELV = (ExpandableListView) findViewById(R.id.ELV);
        mELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent=new Intent(MainActivity47.this,MainActivity47_2.class);
                intent.putExtra("id",groupPosition);
                if(groupPosition==0){
                    intent.putExtra("xianlu","光谷金融街——南湖商厦");
                } else if (groupPosition == 1) {
                    intent.putExtra("xianlu","德州职业——德州站");
                }
                startActivity(intent);


                return false;
            }
        });
        mDd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity47.this,WDDD.class);
                startActivity(intent);
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
        new H().sendResuilt("get_bus_info", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s=response.body().string();
                try {
                    JSONObject jsonObject1=new JSONObject(s);
                    list=new Gson().fromJson(jsonObject1.optJSONArray("ROWS_DETAIL").toString(),
                            new TypeToken<List<Car>>(){}.getType());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter=new ELV_Adapter(MainActivity47.this,list);
                            mELV.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}