package com.jwei.mysearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jwei.mysearch.item.MyUser;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class activity_register_page extends AppCompatActivity {
    private Button register_next;
    private Button register_back;
    private EditText utel;
    private EditText Upassword1;
    private  EditText Upassword2;
    private boolean check=true;


    private BmobUser bu=new BmobUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一：默认初始化
        Bmob.initialize(this, "c3691faf8b85561c7d207be91a25b9e4");
        setContentView(R.layout.activity_register_page);
        utel=(EditText) findViewById(R.id.number_set);
        Upassword1=(EditText) findViewById(R.id.password_set);
        Upassword2=(EditText) findViewById(R.id.password_again);
        register_next = (Button) findViewById(R.id.register_next);
        register_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Upassword2.getText().toString().equals(Upassword1.getText().toString())){

                    BmobQuery<MyUser> muser=new BmobQuery<MyUser>();
                    muser.addWhereEqualTo("Username",utel.getText().toString());
                    muser.findObjects(new FindListener<MyUser>() {
                        @Override
                        public void done(List<MyUser> list, BmobException e) {
                            if(e==null){
                                if(list.isEmpty()==false){
                                    Toast.makeText(getApplicationContext(),"您的号码已经被注册",Toast.LENGTH_LONG).show();
                                    check=false;
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                                check=false;
                            }
                        }
                    });
                    if(check==true){
                bu.setUsername(utel.getText().toString());
                bu.setPassword(Upassword1.getText().toString());
                    bu.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if(e==null){
                                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(activity_register_page.this,AppStart.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            }} else{
                    Toast.makeText(getApplication(),"您输入的两次密码不同，请确认后重试"+Upassword1.getText().toString()+"      "+Upassword2.getText().toString(),Toast.LENGTH_LONG).show();
            }}
        });

        register_back = (Button) findViewById(R.id.register_back);
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_register_page.this,activity_login_page.class);
                startActivity(intent);
            }
        });
    }
}
