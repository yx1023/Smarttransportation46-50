package com.example.tiku46_50;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity46 extends AppCompatActivity {

    private ImageView mMune45;
    private TextView mDqyh;
    private ViewPager mVP;
    private TextView mWdlk;
    private TextView mDlhj;
    List<Fragment>list=new ArrayList<>();

    WDLK wdlk;
    WDJT wdjt;
    MY my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main46);
        initView();
        my=new MY(this.getSupportFragmentManager(),list);
        mVP.setCurrentItem(2);
        mVP.setAdapter(my);
    }

    private void initView() {
        mMune45 = (ImageView) findViewById(R.id.mune45);
        mDqyh = (TextView) findViewById(R.id.dqyh);
        mVP = (ViewPager) findViewById(R.id.VP);
        mWdlk = (TextView) findViewById(R.id.wdlk);
        mDlhj = (TextView) findViewById(R.id.dlhj);

        wdlk=new WDLK();
        wdjt=new WDJT();

        list.add(wdlk);
        list.add(wdjt);

    }



    class MY extends FragmentPagerAdapter{

        List<Fragment>list=new ArrayList<>();

        public MY(@NonNull FragmentManager fm,List<Fragment>list) {
            super(fm);
            this.list=list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}