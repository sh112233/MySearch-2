package com.jwei.mysearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Set;

import java.util.Vector;

public class activity_setting_page extends Activity {

    ListView listView;
    public Vector<Set> s = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    Button settingPageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        settingPageBack = (Button) findViewById(R.id.settingpage_back);
        settingPageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity_setting_page.this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.set_list);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] set_choice={
            "更换皮肤",
            "清理缓存",
            "给个好评",
            "我要反馈",
            "关于我们"
    };

    public int[] set_icons={R.mipmap.skin,
            R.mipmap.clear,
            R.mipmap.favourable,
            R.mipmap.feedback,
            R.mipmap.about
    };

    class MyAdapter extends BaseAdapter {
        private Context context;

        public MyAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return set_choice.length;
        }

        @Override
        public Object getItem(int i) {
            return set_choice[i];
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
                view = View.inflate(context, R.layout.setting_choice, null);
                vh = new ViewHolder();
                vh.set_c = (TextView) view.findViewById(R.id.set_choice);
                vh.set_i = (ImageView) view.findViewById(R.id.set_icon);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            //Set set = s.get(i);


            //System.out.println("view"+view);

            vh.set_c.setText(set_choice[i]);
            vh.set_i.setImageResource(set_icons[i]);
            return view;
        }

        class ViewHolder{
            TextView set_c;
            ImageView set_i;
        }
    }
}
