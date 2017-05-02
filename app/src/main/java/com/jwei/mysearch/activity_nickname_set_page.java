package com.jwei.mysearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jwei.mysearch.R;

public class activity_nickname_set_page extends AppCompatActivity {
    private Button nickname_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname_set_page);
        nickname_back = (Button) findViewById(R.id.nickname_back);
        nickname_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_nickname_set_page.this,activity_validate_page.class);
                startActivity(intent);
            }
        });
    }
}
