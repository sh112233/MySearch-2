package com.jwei.mysearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class activity_validate_page extends AppCompatActivity {
    private Button validate_next;
    private Button validate_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_page);
        validate_next = (Button) findViewById(R.id.valicate_next);
        validate_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_validate_page.this,activity_nickname_set_page.class);
                startActivity(intent);
            }
        });

        validate_back = (Button) findViewById(R.id.validate_back);
        validate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_validate_page.this,activity_register_page.class);
                startActivity(intent);
            }
        });
    }
}
