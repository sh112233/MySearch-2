package com.jwei.mysearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.jwei.mysearch.R;
import com.jwei.mysearch.adapter.AbstractListAdapter;
import com.jwei.mysearch.item.FootprintTime;
import com.jwei.mysearch.item.Goods;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class activity_footprint_page extends AppCompatActivity {

    private ListView mListview;
    private MultiStyleListAdapter mAdapter;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint_page);

        mListview=(ListView)findViewById(R.id.footprint_list);
        mAdapter=new MultiStyleListAdapter(this);
        mListview.setAdapter(mAdapter);

        createData();
        back = (Button) findViewById(R.id.footprint_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_footprint_page.this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    private void createData(){
        List<Object> dataAll=new ArrayList<>();
        dataAll.add(new FootprintTime("一个星期内"));
        dataAll.add(new Goods("疯狂Android讲义 李刚疯狂的Android讲义教程从入门到精通","瑞意图书专营店","¥88.60",R.mipmap.goods1));
        dataAll.add(new Goods("林宥嘉 大小说家 CD+三张明信片+写真歌词本","天沐音像专营店","¥49.00",R.mipmap.goods2));
        dataAll.add(new Goods("短袖T恤男 经典卡通动物印花圆领TEE","lifeafterlife旗舰店","¥59.00",R.mipmap.goods3));
        dataAll.add(new FootprintTime("一个月内"));
        dataAll.add(new Goods("马可彩铅笔72/48色油性彩铅专业绘画美术填图笔","标逸办公专营店","¥72.00",R.mipmap.goods4));
        dataAll.add(new Goods("威诺时男士高档学生潮流时装表防水手表","西子表屋","¥168.00",R.mipmap.goods5));
        dataAll.add(new Goods("威爵士男女士牛奶滋润洗发水去屑洗发露正品留香牛奶味","创美时尚馆美发护发正品店","¥38.00",R.mipmap.goods6));
        mAdapter.setList(dataAll);
        mAdapter.notifyDataSetChanged();
    }



    class MultiStyleListAdapter extends AbstractListAdapter<Object> {
        private Class[] dataClasses;
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            int viewType=getItemViewType(position);
            if (viewType==0) {
                view = getStyle1View(position, view, parent);
            }else if (viewType==1) {
                view = getStyle2View(position, view, parent);
            }

            return view;
        }

        public MultiStyleListAdapter(Context context) {
            // TODO Auto-generated constructor stub
            super(context);
            dataClasses=new Class[]{FootprintTime.class,Goods.class};
        }

        /**
         * 注意返回的类型：
         * 如果只有1种布局类型，那么返回的type是0；
         * 如果2种类型，必须是0,1
         * 如果3种类型，必须是0,1,2
         * 。。。。
         * 依次类推
         * @param position 根据position返回对应位置的视图类型
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            // TODO Auto-generated method stub
            Object object=getItem(position);
            for(int i=0,size=dataClasses.length;i<size;i++){
                if(object.getClass()== dataClasses[i]){
                    return i;
                }
            }
            return 0;
        }

        /**
         * @return 返回值是，布局种类总数
         */
        @Override
        public int getViewTypeCount() {
            // TODO Auto-generated method stub
            return dataClasses.length;
        }


        private View getStyle1View(final int position, View convertView,
                                   ViewGroup parent) {
            final Styly1ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.time, null);
                holder = new Styly1ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Styly1ViewHolder) convertView.getTag();
            }
            final FootprintTime item=(FootprintTime) mList.get(position);
            holder.footprinttime.setText(item.time);
            return convertView;
        }

        private View getStyle2View(final int position, View convertView,
                                   ViewGroup parent) {
            final Styly2ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.collection_goods_item, null);
                holder = new Styly2ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Styly2ViewHolder) convertView.getTag();
            }
            final Goods item=(Goods)mList.get(position);
            holder.t11.setText(item.Goods_name);
            holder.t22.setText(item.Store_name);
            holder.t33.setText(item.Price);
            holder.iv1.setImageResource(item.Goods_imageid);
            return convertView;
        }


        class Styly1ViewHolder {
            TextView footprinttime;

            public Styly1ViewHolder(View root){
                footprinttime=(TextView)root.findViewById(R.id.footprint_time);
            }

        }

        class Styly2ViewHolder {
            TextView t11;
            TextView t22;
            TextView t33;
            ImageView iv1;

            public Styly2ViewHolder(View root){
                iv1=(ImageView)root.findViewById(R.id.collect_goods_imageview);
                t11=(TextView)root.findViewById(R.id.collect_goods_name);
                t22=(TextView)root.findViewById(R.id.store_name1);
                t33=(TextView)root.findViewById(R.id.collection_goods_price);
            }

        }


    }
}
