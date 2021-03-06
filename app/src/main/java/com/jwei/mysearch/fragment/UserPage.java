package com.jwei.mysearch.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jwei.mysearch.CollectionPage;
import com.jwei.mysearch.R;
import com.jwei.mysearch.activity_about_page;
import com.jwei.mysearch.activity_footprint_page;
import com.jwei.mysearch.activity_profile_page;
import com.jwei.mysearch.activity_setting_page;
import com.jwei.mysearch.activity_share_page;
import com.jwei.mysearch.item.Set;
import java.util.Vector;

/**
 * Created by Administrator on 2017/3/15.
 */

public class UserPage extends Fragment {
//    protected static final int CHOOSE_PICTURE = 0;
//    protected static final int TAKE_PICTURE = 1;
//    private static final int CROP_SMALL_PICTURE = 2;
//    protected static Uri tempUri;
    ListView listView;
    public Vector<Set> s = new Vector<>();
    public MyAdapter myAdapter;
    public static final int loading=0x1;
    public Button collect_goods;
    public Button collect_store;

    private ImageView iv_personal_icon;
    private ImageView personal_icon;
    private ImageView toSetting;
    private Bitmap icon;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_page, container,false);

        collect_goods = (Button) view.findViewById(R.id.collect_goods);
        collect_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectionPage.class);
                startActivity(intent);
            }
        });

        iv_personal_icon = (ImageView) view.findViewById(R.id.iv_personal_icon);
        iv_personal_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_profile_page.class);
                startActivity(intent);
            }
        });

        toSetting = (ImageView) view.findViewById(R.id.to_settingpage);
        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),activity_setting_page.class);
                startActivity(intent);
            }
        });

        listView = (ListView) view.findViewById(R.id.user_page_choice);
        myAdapter = new MyAdapter(getActivity());
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView set = (TextView) view.findViewById(R.id.set_choice);
                String s = (String) set.getText();
                switch (s)
                {
                    case "我的发现":
                        Intent intent = new Intent(getActivity(),activity_share_page.class);
                        startActivity(intent);
                        break;
                    case "我的足迹":
                        intent = new Intent(getActivity(),activity_footprint_page.class);
                        startActivity(intent);
                        break;
                    case "关于我们":
                        intent = new Intent(getActivity(),activity_about_page.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        collect_store = (Button) view.findViewById(R.id.collect_store);
        collect_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectionPage.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        return view;
    }

    public int index = 0;
    /*
    * 初始化数据
    * */

    public String[] set_choice={
            "我的发现",
            "我的足迹",
            "关于我们"
    };

    public int[] set_icons={R.mipmap.discover,
            R.mipmap.footprint2,
            R.mipmap.about2
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


    /**
     * 显示修改头像的对话框
     */
//    protected void showChoosePicDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("设置头像");
//        String[] items = { "选择本地照片", "拍照" };
//        builder.setNegativeButton("取消", null);
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case CHOOSE_PICTURE: // 选择本地照片
//                        Intent openAlbumIntent = new Intent(
//                                Intent.ACTION_GET_CONTENT);
//                        openAlbumIntent.setType("image/*");
//                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
//                        break;
//                    case TAKE_PICTURE: // 拍照
//                        Intent openCameraIntent = new Intent(
//                                MediaStore.ACTION_IMAGE_CAPTURE);
//                        tempUri = Uri.fromFile(new File(Environment
//                                .getExternalStorageDirectory(), "image.jpg"));
//                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
//                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
//                        break;
//                }
//            }
//        });
//        builder.create().show();
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
//            switch (requestCode) {
//                case TAKE_PICTURE:
//                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
//                    break;
//                case CHOOSE_PICTURE:
//                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
//                    break;
//                case CROP_SMALL_PICTURE:
//                    if (data != null) {
//                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
//                    }
//                    break;
//            }
//        }
//    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
//    protected void startPhotoZoom(Uri uri) {
//        if (uri == null) {
//            Log.i("tag", "The uri is not exist.");
//        }
//        tempUri = uri;
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        // 设置裁剪
//        intent.putExtra("crop", "true");
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, CROP_SMALL_PICTURE);
//    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     * @param data
     */
//    protected void setImageToView(Intent data) {
//        Bundle extras = data.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
//            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
//            iv_personal_icon.setImageBitmap(photo);
//            uploadPic(photo);
//        }
//    }

//    private void uploadPic(Bitmap bitmap) {
//        // 上传至服务器
//        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
//        // 注意这里得到的图片已经是圆形图片了
//        // bitmap是没有做个圆形处理的，但已经被裁剪了
//
//        String imagePath = Utils.savePhoto(bitmap, Environment
//                .getExternalStorageDirectory().getAbsolutePath(), String
//                .valueOf(System.currentTimeMillis()));
//        Log.e("imagePath", imagePath+"");
//        if(imagePath != null){
//            // 拿着imagePath上传了
//            // ...
//        }
//    }
}
