package com.jwei.mysearch.adapter;

/**
 * Created by Administrator on 2016/12/19.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwei.mysearch.item.Goods;

import java.util.List;

/**
 * Created by chen on 2016/12/19.
 */

public class GoodsAdapter extends ArrayAdapter<Goods>
{
    private int resourceId;
    public GoodsAdapter(Context context, int textViewResourceId, List<Goods>objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
//        Goods goods= getItem(position);
//        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
//        ImageView goodsimage= (ImageView)view.findViewById(R.id.goods_image);
//        TextView goodsname=(TextView)view.findViewById(R.id.goods_name);
//        goodsimage.setImageResource(goods.getGoods_imageid());
//        goodsname.setText(goods.getGoods_name());
          return convertView;
    }
}

