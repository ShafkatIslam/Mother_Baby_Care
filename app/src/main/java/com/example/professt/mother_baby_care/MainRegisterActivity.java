package com.example.professt.mother_baby_care;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainRegisterActivity extends AppCompatActivity {

    Button motherButton,childButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        motherButton = (Button)findViewById(R.id.motherButton);
        childButton = (Button)findViewById(R.id.childButton);

        motherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainRegisterActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        childButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainRegisterActivity.this,BabyRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {           //creating method of onBackPressed

        Intent intent =  new Intent(MainRegisterActivity.this,MainActivity.class);
        startActivity(intent);

    }

}
