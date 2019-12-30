package com.example.professt.mother_baby_care.Baby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.professt.mother_baby_care.R;

public class ChildTipsShowActivity extends AppCompatActivity {

    ImageView imageView,imageView1,imageView2,imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_tips_show);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);

        Glide.with(this).load((R.drawable.tipsd))
                .placeholder(R.drawable.load)
                //.override(120, 120) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .fitCenter()   // scale to fit entire image within ImageView
                .into(imageView);

        Glide.with(this).load((R.drawable.tipsg))
                .placeholder(R.drawable.load)
                //.override(120, 120) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .fitCenter()   // scale to fit entire image within ImageView
                .into(imageView1);

        Glide.with(this).load((R.drawable.tipse))
                .placeholder(R.drawable.load)
                //.override(120, 120) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .fitCenter()   // scale to fit entire image within ImageView
                .into(imageView2);

        Glide.with(this).load((R.drawable.tipsh))
                .placeholder(R.drawable.load)
                //.override(120, 120) // resizes the image to 100x200 pixels but does not respect aspect ratio
                .fitCenter()   // scale to fit entire image within ImageView
                .into(imageView3);
    }
}
