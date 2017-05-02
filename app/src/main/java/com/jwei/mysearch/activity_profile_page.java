package com.jwei.mysearch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jwei.mysearch.R;
import com.jwei.mysearch.item.Profile;
import com.jwei.mysearch.other.RenderScriptGaussianBlur;
import com.jwei.mysearch.other.Utils;

import java.io.File;
import java.util.Vector;

public class activity_profile_page extends AppCompatActivity{
    ListView listView1,listView2,listView3;
    public Vector<Profile> p1 = new Vector<>();
    public Vector<Profile> p2 = new Vector<>();
    public Vector<Profile> p3 = new Vector<>();
    public MyAdapter1 myAdapter1;
    public MyAdapter2 myAdapter2;
    public MyAdapter3 myAdapter3;
    private TextView sig;
    private ImageView imageView;
    private RenderScriptGaussianBlur blur;
    private Button profile_back;
    private Button profile_edit;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    public Bitmap icon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        imageView = (ImageView) findViewById(R.id.profile_personal_icon_blur);
        iv_personal_icon = (ImageView) findViewById(R.id.profile_personal_icon);

        iv_personal_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        blur = new RenderScriptGaussianBlur(activity_profile_page
                .this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.example3);
        bitmap = blur.big(bitmap);
        imageView.setImageBitmap(blur.gaussianBlur(20,bitmap));

        profile_back = (Button) findViewById(R.id.profile_back);
        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_profile_page.this,MainPages.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        profile_edit = (Button) findViewById(R.id.profile_edit);
        profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_profile_page.this,activity_profile_edit.class);
                startActivity(intent);
            }
        });


//        sig = (TextView) findViewById(R.id.profile_user_abstract);
//        sig.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(activity_profile_page.this,activity_signature_change.class);
//                startActivity(intent);
//            }
//        });

        listView1 = (ListView) findViewById(R.id.profile_list);
        myAdapter1 = new MyAdapter1(this);
        listView1.setAdapter(myAdapter1);

        listView2 = (ListView) findViewById(R.id.profile_list2);
        myAdapter2 = new MyAdapter2(this);
        listView2.setAdapter(myAdapter2);

        listView3 = (ListView) findViewById(R.id.profile_list3);
        myAdapter3 = new MyAdapter3(this);
        listView3.setAdapter(myAdapter3);

    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale",true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("noFaceDetection", "true");
        intent.putExtra("return-data",true);
        startActivityForResult(intent,CROP_SMALL_PICTURE);

    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            iv_personal_icon.setImageBitmap(photo);
            //photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            imageView.setImageBitmap(photo);
            blur = new RenderScriptGaussianBlur(activity_profile_page.this);
            photo = blur.big(photo);
            imageView.setImageBitmap(blur.gaussianBlur(20,photo));
            uploadPic(photo);
            icon1 = photo;
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
        }
    }

    public String[] info1={
            "性别",
            "年龄"
    };

    public String[] info1_content={
            "男",
            "23"
    };

    public String[] info2={
            "联系方式",
            "邮箱"
    };

    public String[] info2_content={
            "18057894279",
            "651903069@qq.com"
    };

    public String[] info3={
            "个性签名"
    };

    public String[] info3_content={
            "介绍一下自己吧"
    };

    class MyAdapter1 extends BaseAdapter {
        private Context context;

        public MyAdapter1(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return info1.length;
        }

        @Override
        public Object getItem(int i) {
            return info1[i];
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
                view = View.inflate(context, R.layout.profile, null);
                vh = new ViewHolder();
                vh.info1 = (TextView) view.findViewById(R.id.info);
                vh.info_c1 = (TextView) view.findViewById(R.id.info_content);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            //Set set = s.get(i);


            //System.out.println("view"+view);

            vh.info1.setText(info1[i]);
            vh.info_c1.setText(info1_content[i]);
            return view;
        }

        class ViewHolder{
            TextView info1;
            TextView info_c1;
        }
    }

    class MyAdapter2 extends BaseAdapter {
        private Context context;

        public MyAdapter2(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return info2.length;
        }

        @Override
        public Object getItem(int i) {
            return info2[i];
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
                view = View.inflate(context, R.layout.profile, null);
                vh = new ViewHolder();
                vh.info2 = (TextView) view.findViewById(R.id.info);
                vh.info_c2 = (TextView) view.findViewById(R.id.info_content);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            //Set set = s.get(i);


            //System.out.println("view"+view);

            vh.info2.setText(info2[i]);
            vh.info_c2.setText(info2_content[i]);
            return view;
        }

        class ViewHolder{
            TextView info2;
            TextView info_c2;
        }
    }

    class MyAdapter3 extends BaseAdapter {
        private Context context;

        public MyAdapter3(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return info3.length;
        }

        @Override
        public Object getItem(int i) {
            return info3[i];
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
                view = View.inflate(context, R.layout.profile, null);
                vh = new ViewHolder();
                vh.info3 = (TextView) view.findViewById(R.id.info);
                vh.info_c3 = (TextView) view.findViewById(R.id.info_content);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            //Set set = s.get(i);


            //System.out.println("view"+view);

            vh.info3.setText(info3[i]);
            vh.info_c3.setText(info3_content[i]);
            return view;
        }

        class ViewHolder{
            TextView info3;
            TextView info_c3;
        }
    }

}
