package com.jwei.mysearch;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.jwei.mysearch.R;
import com.jwei.mysearch.fragment.GoodsCollection;
import com.jwei.mysearch.fragment.StoreCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CollectionPage extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager pager;
    private List<String> list;
    private Button collectionpage_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_collection_page);

        collectionpage_back = (Button) findViewById(R.id.collectionpage_back);
        collectionpage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollectionPage
                        .this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        /*初始化界面*/
        initViews();
        /*初始化数据*/
        initData();
        /*设置Adapter*/
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(),list));
        /*Tab与ViewPager绑定*/
        tab.setupWithViewPager(pager);
        int id = getIntent().getIntExtra("id",0);
        if (id==1) {
            pager.setCurrentItem(1);
        }
    }


    /*初始化数据*/
    private void initData() {
        list = new ArrayList<>();
        list.add(String.format(Locale.CHINA,"已收藏商品",0));
        list.add(String.format(Locale.CHINA,"已收藏商店",1));
    }

    /*初始化界面*/
    private void initViews() {
        this.pager = (ViewPager) findViewById(R.id.collect_pager);
        this.tab = (TabLayout) findViewById(R.id.tab_FindFragment_title);
    }


    class MyAdapter extends FragmentPagerAdapter {

        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            if(position ==0) {
                GoodsCollection goodscollection = new GoodsCollection();
                return goodscollection;
            }else {
                StoreCollection storecollection = new StoreCollection();
                return storecollection;
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position ==0) {
                return "已收藏商品";
            }else {
                return "已收藏商店";
            }
        }
    }
}



