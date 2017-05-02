package com.jwei.mysearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jwei.mysearch.R;

import java.util.Date;


public class activity_profile_edit extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button profile_edit_back;
    private Spinner sex;
    private TextView age_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_edit);

        profile_edit_back = (Button) findViewById(R.id.profile_edit_back);
        profile_edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_profile_edit.this,activity_profile_page.class);
                startActivity(intent);
            }
        });

        sex = (Spinner) findViewById(R.id.sex_edit);
        ArrayAdapter< String> adapter = new ArrayAdapter< String>( this,R.layout.spinner_style);
        adapter.add("男");
        adapter.add("女");
        sex.setAdapter(adapter);
        age_edit = (TextView) findViewById(R.id.age_edit);
    }

    public void age_set(View v) {
        new DatePickerDialog(activity_profile_edit.this,this,2000,0,1).show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Toast.makeText(activity_profile_edit.this, "你选择的是" + i + "年" + (i1+1) + "月" + i2 + "日",
                Toast.LENGTH_LONG).show();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        int NowYear = Integer.parseInt(format.format(new Date()))-i;
        age_edit.setText(String.valueOf(NowYear));
    }
}
