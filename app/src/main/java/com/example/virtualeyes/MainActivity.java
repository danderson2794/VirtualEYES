package com.example.virtualeyes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import android.net.Uri;



public class MainActivity extends AppCompatActivity {

    private static int VIDEO_REQUEST = 101;
    private Uri videoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void captureVideo(View view)
    {
        Intent videoInt = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if ( videoInt.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(videoInt, VIDEO_REQUEST);
        }
    }

    public void playVideo(View view)
    {
        Intent playIntent = new Intent(this, VideoPlayActivity.class);
        playIntent.putExtra("videoUri", videoUri.toString());
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            videoUri = data.getData();
        }
    }
}
