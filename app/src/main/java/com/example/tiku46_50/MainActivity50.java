package com.example.tiku46_50;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity50 extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFanhui;
    private TextView mR1;
    private TextView mR2;
    private TextView mR3;
    private TextView mR4;
    private TextView mR5;
    private TextView mR6;
    private TextView mR7;
    private BarChart bc;

    List<LK>list=new ArrayList<>();
    List<List<LK>>lists=new ArrayList<>();
    private List<BarEntry> entries1;
    private List<BarEntry> entries2;
    private List<BarEntry> entries3;
    private List<BarEntry> entries4;
    private List<BarEntry> entries5;
    private List<BarEntry> entries6;
    private List<BarEntry> entries7;
    int r1=1,r2=1,r3=1,r4=1,r5=1,r6=1,r7=1;
    boolean a=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main50);
        initView();

       Timer timer=new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               list=new ArrayList<>();
               lists=new ArrayList<>();
               for (int i = 0; i < 7; i++) {
                   getData();
               }
           }
       },0,5000);


    }



    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.fanhui);
        mR1 = (TextView) findViewById(R.id.r1);
        mR2 = (TextView) findViewById(R.id.r2);
        mR3 = (TextView) findViewById(R.id.r3);
        mR4 = (TextView) findViewById(R.id.r4);
        mR5 = (TextView) findViewById(R.id.r5);
        mR6 = (TextView) findViewById(R.id.r6);
        mR7 = (TextView) findViewById(R.id.r7);
        bc = (BarChart) findViewById(R.id.BC);
        mR1.setOnClickListener(this);
        mR2.setOnClickListener(this);
        mR3.setOnClickListener(this);
        mR4.setOnClickListener(this);
        mR5.setOnClickListener(this);
        mR6.setOnClickListener(this);
        mR7.setOnClickListener(this);

    }

    public void getData(){

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("RoadId",0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        new H().sendResuilt("get_road_status", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String s=response.body().string();
                try {
                    JSONObject jsonObject1=new JSONObject(s);
                    list=new Gson().fromJson(jsonObject1.optJSONArray("ROWS_DETAIL").toString(),
                            new TypeToken<List<LK>>(){}.getType());
                    lists.add(list);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadData();
                        }
                    });



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //设置Title
    private void setDesc() {
        Description description = bc.getDescription();



        description.setPosition(350,50);
        description.setText("道路拥堵状况");
        description.setTextSize(25f);
        description.setTextColor(Color.BLACK);
        bc.setDescription(description);
    }

    //设置图例
    private void setLegend() {
        Legend legend = bc.getLegend();
        legend.setEnabled(false);
    }

    //设置Y轴
    private void setYAxis() {

        YAxis yAxis = bc.getAxisLeft();
        yAxis.setTextSize(14f);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setAxisLineWidth(3f);

        String[]strings={"畅通","畅通","缓行","一般拥堵","中度拥堵","严重拥堵"};
        yAxis.setValueFormatter(new IndexAxisValueFormatter(strings));

        yAxis.setLabelCount(5);
        YAxis yAxis1=bc.getAxisRight();
        yAxis1.setAxisLineWidth(3f);


    }

    //设置X轴
    private void setXAxis() {
        XAxis xAxis = bc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(16f);
        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(3f);


        String[]time={"周一","周二","周三","周四","周五","周六","周日"};
        //自定义格式
       xAxis.setValueFormatter(new IndexAxisValueFormatter(time));

    }

    //设置数据
    private void loadData() {
        setDesc();
        setLegend();
        setXAxis();
        setYAxis();


        bc.setExtraOffsets(30,70,30,50);
        bc.animateXY(1000,1000);
        bc.setFitBars(true);


        entries1 = new ArrayList<BarEntry>();
        entries2 = new ArrayList<BarEntry>();
        entries3 = new ArrayList<BarEntry>();
        entries4 = new ArrayList<BarEntry>();
        entries5 = new ArrayList<BarEntry>();
        entries6 = new ArrayList<BarEntry>();
        entries7 = new ArrayList<BarEntry>();

        try {
            for (int i = 0; i < 7; i++) {
                entries1.add(new BarEntry(i,lists.get(0).get(i).getState()));
                entries2.add(new BarEntry(i,lists.get(1).get(i).getState()));
                entries3.add(new BarEntry(i,lists.get(2).get(i).getState()));
                entries4.add(new BarEntry(i,lists.get(3).get(i).getState()));
                entries5.add(new BarEntry(i,lists.get(4).get(i).getState()));
                entries6.add(new BarEntry(i,lists.get(5).get(i).getState()));
                entries7.add(new BarEntry(i,lists.get(6).get(i).getState()));
            }

        }catch (Exception e){
            System.out.println("");
        }







        BarDataSet barDataSet1 = new BarDataSet(entries1,"");
        BarDataSet barDataSet2 = new BarDataSet(entries2,"");
        BarDataSet barDataSet3 = new BarDataSet(entries3,"");
        BarDataSet barDataSet4 = new BarDataSet(entries4,"");
        BarDataSet barDataSet5= new BarDataSet(entries5,"");
        BarDataSet barDataSet6 = new BarDataSet(entries6,"");
        BarDataSet barDataSet7= new BarDataSet(entries7,"");

        barDataSet1.setColor(Color.parseColor("#AA0B00"));
        barDataSet2.setColor(Color.parseColor("#00116E"));
        barDataSet3.setColor(Color.parseColor("#419FE7"));
        barDataSet4.setColor(Color.parseColor("#E38700"));
        barDataSet5.setColor(Color.parseColor("#00B5A4"));
        barDataSet6.setColor(Color.parseColor("#004A43"));
        barDataSet7.setColor(Color.parseColor("#734500"));

        barDataSet1.setDrawValues(false);
        barDataSet2.setDrawValues(false);
        barDataSet3.setDrawValues(false);
        barDataSet4.setDrawValues(false);
        barDataSet5.setDrawValues(false);
        barDataSet6.setDrawValues(false);
        barDataSet7.setDrawValues(false);





        List<IBarDataSet> barDataSets = new ArrayList<IBarDataSet>();
        barDataSets.add(barDataSet1);
        barDataSets.add(barDataSet2);
        barDataSets.add(barDataSet3);
        barDataSets.add(barDataSet4);
        barDataSets.add(barDataSet5);
        barDataSets.add(barDataSet6);
        barDataSets.add(barDataSet7);


        BarData barData = new BarData(barDataSets);
        barData.setBarWidth(0.1f);//设置柱块的宽度
        //参数1：距左边的距离（开始会偏移一个组的距离）
        //参数二：组与组之间的间距
        //参数三：一组柱块之中每个之间的距离
        barData.groupBars(-0.55f,0.3f,0);

        bc.setData(barData);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:
                if(r1==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries1.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r1=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries1.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r1=1;
                }

                break;
            case R.id.r2:
                if(r2==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries2.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r2=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries2.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r2=1;
                }
                break;
            case R.id.r3:
                if(r3==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries3.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r3=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries3.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r3=1;
                }
                break;
            case R.id.r4:
                if(r4==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries4.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r4=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries4.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r4=1;
                }
                break;
            case R.id.r5:
                if(r5==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries5.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r5=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries5.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r5=1;
                }
                break;
            case R.id.r6:
                if(r6==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries6.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r6=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries6.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r6=1;
                }
                break;
            case R.id.r7:
                if(r7==1){
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries7.get(0));
                    dataSet1.setVisible(false);
                    bc.invalidate();
                    r7=2;
                }else {
                    BarDataSet dataSet1 = (BarDataSet) bc.getBarData().getDataSetForEntry(entries7.get(0));
                    dataSet1.setVisible(true);
                    bc.invalidate();
                    r7=1;
                }
                break;
        }
    }
}