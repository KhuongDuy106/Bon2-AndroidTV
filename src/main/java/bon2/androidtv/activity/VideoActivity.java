package bon2.androidtv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import bon2.androidtv.R;
import bon2.androidtv.helper.RoundedImageView;

public class VideoActivity extends Activity implements View.OnClickListener{

    LinearLayout layout;
    RoundedImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        layout = findViewById(R.id.video_layout);
        img1 = findViewById(R.id.img1);
        img1.setOnClickListener(this);

        img2 = findViewById(R.id.img2);
        img2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, VideoItemActivity.class);
        startActivity(i);
    }
}
