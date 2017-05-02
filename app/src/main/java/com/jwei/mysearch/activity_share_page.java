package com.jwei.mysearch;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Goods;

import java.util.Vector;

public class activity_share_page extends AppCompatActivity implements AbsListView.OnScrollListener{
    ListView listView;
    public Vector<Goods> goods = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    Button share_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_page);
        listView = (ListView) findViewById(R.id.share_list);
        listView.setOnScrollListener(this);
        initData();
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);

        share_back = (Button) findViewById(R.id.share_back);
        share_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_share_page.this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] Goods_name={
            "疯狂Android讲义 李刚疯狂的Android讲义教程从入门到精通",
            "林宥嘉 大小说家 CD+三张明信片+写真歌词本",
            "短袖T恤男 经典卡通动物印花圆领TEE",
            "马可彩铅笔72/48色油性彩铅专业绘画美术填图笔"
    };

    public String[] Store_name={
            "瑞意图书专营店",
            "天沐音像专营店",
            "lifeafterlife旗舰店",
            "标逸办公专营店"
    };

    public String[] Price={
            "¥88.60",
            "¥49.00",
            "¥59.00",
            "¥72.00"
    };

    public int[] images={R.mipmap.goods1,
            R.mipmap.goods2,
            R.mipmap.goods3,
            R.mipmap.goods4
    };

    public void initData(){
        for(int i=0;i < 10&&index<Goods_name.length;i++){
            Goods g = new Goods();
            g.Goods_name = Goods_name[index];
            g.Store_name = Store_name[index];
            g.Price = Price[index];
            g.Goods_imageid = images[index];
            ++index;
            goods.add(g);
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
            return goods.size();
        }

        @Override
        public Object getItem(int i) {
            return goods.get(i);
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
                view = View.inflate(context, R.layout.collection_goods_item, null);
                vh = new ViewHolder();
                vh.t11 = (TextView) view.findViewById(R.id.collect_goods_name);
                vh.t22 = (TextView) view.findViewById(R.id.store_name1);
                vh.t33 = (TextView) view.findViewById(R.id.collection_goods_price);
                vh.iv1 = (ImageView) view.findViewById(R.id.collect_goods_imageview);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            Goods g = goods.get(i);


            //System.out.println("view"+view);

            vh.t11.setText(g.Goods_name);
            vh.t22.setText(g.Store_name);
            vh.t33.setText(g.Price);
            vh.iv1.setImageResource(g.Goods_imageid);
            return view;
        }

        class ViewHolder{
            TextView t11;
            TextView t22;
            TextView t33;
            ImageView iv1;
        }
    }
}
