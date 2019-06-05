package com.example.newsapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class NewsDetails extends AppCompatActivity {

    private ImageView  imgDetails;
    private TextView titleDetails, descDetails, authorDetails, publishedAtDetails, sourceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        imgDetails = (ImageView)findViewById(R.id.imgDetails);
        titleDetails = (TextView)findViewById(R.id.titleDetails);
        descDetails = (TextView)findViewById(R.id.descDetails);
        authorDetails = (TextView)findViewById(R.id.authorDetails);
        publishedAtDetails = (TextView)findViewById(R.id.publishedAtDetails);
       // sourceDetails = (TextView)findViewById(R.id.sourceDetails);


        HashMap<String, String> hashMap = (HashMap<String, String>) getIntent().getSerializableExtra(Adapter.HASH_EXTRA);
         String title = hashMap.get(Adapter.TITLE);
        String image = hashMap.get(Adapter.IMAGE);
        String desc = hashMap.get(Adapter.DESC);
        String author = hashMap.get(Adapter.AUTHOR);
        String publishedAt = hashMap.get(Adapter.PUBLISHEDAT);
      //  String source = hashMap.get(Adapters);

        Glide.with(getApplicationContext()).load(image).into(imgDetails);
        titleDetails.setText(title);
        descDetails.setText(desc);
        authorDetails.setText(author);
        publishedAtDetails.setText(publishedAt);

    }
}
