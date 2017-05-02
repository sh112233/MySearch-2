package com.jwei.mysearch;

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

public class activity_about_page extends AppCompatActivity {
    ListView listView;
    //public Vector<Set> s = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        listView = (ListView) findViewById(R.id.about_choice);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        back = (Button) findViewById(R.id.about_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_about_page.this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] set_choice={
            "版本更新",
            "帮助说明",
            "反馈"
    };

    public int[] set_icons={R.mipmap.version,
            R.mipmap.help,
            R.mipmap.feedback1
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
