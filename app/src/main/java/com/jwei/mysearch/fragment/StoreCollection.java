package com.jwei.mysearch.fragment;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Store;

import java.util.Vector;

public class StoreCollection extends Fragment implements AbsListView.OnScrollListener {
    ListView listView;
    public Vector<Store> store = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;

    public static StoreCollection newInstance(String text){
        Bundle bundle = new Bundle();
        bundle.putString("已收藏商店",text);
        StoreCollection storeCollection = new StoreCollection();
        storeCollection.setArguments(bundle);
        return  storeCollection;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_store_collection, container,false);
        listView = (ListView) view.findViewById(R.id.store_collect_list);
        listView.setOnScrollListener(this);
        initData();
        myAdapter = new MyAdapter(getActivity());
        listView.setAdapter(myAdapter);
        return view;
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] Store_name={
            "万嘉超市",
            "新华书店",
            "永辉超市",
            "沃尔玛",
            "pull and bear 旗舰店",
            "苏宁电器"
    };

    public String[] Distance={
            "距离:2.6km",
            "距离:4.8km",
            "距离:4.5km",
            "距离:1.5km",
            "距离:6.5km",
            "距离:3.3km"
    };

    public int[] images={R.mipmap.store1,
            R.mipmap.store2,
            R.mipmap.store3,
            R.mipmap.store4,
            R.mipmap.store5,
            R.mipmap.store6
    };

    public void initData(){
        for(int i=0;i < 10&&index<Store_name.length;i++){
            Store s = new Store();
            s.Store_name = Store_name[index];
            s.Distance = Distance[index];
            s.Store_imageid = images[index];
            ++index;
            store.add(s);
        }
    }

    private int visibleLastIndex;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if(i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && myAdapter.getCount()==visibleLastIndex){
            new LoadThread().start();

        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        visibleLastIndex =i+i1-1;
    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case loading :
                    myAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    class LoadThread extends Thread{
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通过handler给主线程发送一个标记
            handler.sendEmptyMessage(1);
        }
    }

    class MyAdapter extends BaseAdapter {
        private Context context;

        public MyAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return store.size();
        }

        @Override
        public Object getItem(int i) {
            return store.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder vh;


            if(view == null) {
                LayoutInflater inflater = LayoutInflater.from(this.context);
                //实例化一个布局
                view = View.inflate(context, R.layout.store_items, null);
                vh = new ViewHolder();
                vh.t22 = (TextView) view.findViewById(R.id.collect_store_name);
                vh.t33 = (TextView) view.findViewById(R.id.collect_store_distance);
                vh.iv1 = (ImageView) view.findViewById(R.id.store_imageview);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            Store g = store.get(i);


            //System.out.println("view"+view);
            vh.t22.setText(g.Store_name);
            vh.t33.setText(g.Distance);
            vh.iv1.setImageResource(g.Store_imageid);
            return view;
        }

        class ViewHolder{
            TextView t22;
            TextView t33;
            ImageView iv1;
        }
    }
}
