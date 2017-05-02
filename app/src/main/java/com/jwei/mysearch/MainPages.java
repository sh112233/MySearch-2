package com.jwei.mysearch;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jwei.mysearch.R;
import com.jwei.mysearch.fragment.SearchPage;
import com.jwei.mysearch.fragment.UserPage;

public class MainPages extends FragmentActivity implements View.OnClickListener{
    //private TextView topBar;
    private Button tabSearch;
    //private TextView tabMore;
    private Button tabUser;

    private SearchPage searchPage;
    private UserPage userPage;
    private FragmentManager fragmentManager;

    public static final String POSITION = "position";
    public static final int FRAGMENT_ONE=0;
    public static final int FRAGMENT_TWO=1;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_page);
        fragmentManager = getSupportFragmentManager();
        bindView();
        showFragment(FRAGMENT_ONE);
        tabSearch.setSelected(true);
        int id = getIntent().getIntExtra("id",0);
        if (id==1) {
            showFragment(FRAGMENT_TWO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //屏幕旋转时记录位置
        outState.putInt(POSITION,position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //屏幕恢复时取出位置
        showFragment(savedInstanceState.getInt(POSITION));
        super.onRestoreInstanceState(savedInstanceState);
    }


    //UI组件初始化与事件绑定
    private void bindView() {
        //topBar = (TextView)this.findViewById(R.id.txt_top);
        tabSearch = (Button)this.findViewById(R.id.txt_deal);
        tabUser = (Button) this.findViewById(R.id.txt_user);
        //tabMore = (TextView)this.findViewById(R.id.txt_more);

        tabSearch.setOnClickListener(this);
//        tabMore.setOnClickListener(this);
        tabUser.setOnClickListener(this);

    }

    public void showFragment(int index){

        FragmentTransaction ft=fragmentManager.beginTransaction();
        hideFragment(ft);

        //注意这里设置位置
        position = index;

        switch (index){

            case FRAGMENT_ONE:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (searchPage==null){
                    searchPage=new SearchPage();
                    ft.add(R.id.fragment_container, searchPage );
                    tabSearch.setSelected(true);
                }else {
                    ft.show(searchPage);
                    tabSearch.setSelected(true);
                }

                break;
            case FRAGMENT_TWO:
                if (userPage==null){
                    userPage=new UserPage();
                    ft.add(R.id.fragment_container,userPage);
                    tabUser.setSelected(true);
                }else {
                    ft.show(userPage);
                    tabUser.setSelected(true);
                }

                break;
        }

        ft.commit();
    }

    //隐藏所有Fragment
    public void hideFragment(FragmentTransaction ft){
        //如果不为空，就先隐藏起来
        if (searchPage!=null){
            ft.hide(searchPage);
            tabSearch.setSelected(false);
        }
        if(userPage!=null) {
            ft.hide(userPage);
            tabUser.setSelected(false);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.txt_deal:
                showFragment(FRAGMENT_ONE);
                break;
            case R.id.txt_user:
                showFragment(FRAGMENT_TWO);
                break;

        }
    }
}
