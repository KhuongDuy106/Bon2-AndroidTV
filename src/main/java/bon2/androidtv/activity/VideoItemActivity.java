package bon2.androidtv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import bon2.androidtv.R;
import bon2.androidtv.helper.RoundedImageView;

public class VideoItemActivity extends Activity implements View.OnClickListener{

    RoundedImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_item);

        img = findViewById(R.id.img1);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.finish();
        Intent i = new Intent(this, LoadingActivity.class);
        startActivity(i);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(VideoItemActivity.this, VideoItemDetailActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
