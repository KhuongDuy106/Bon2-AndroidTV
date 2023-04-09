package bon2.androidtv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bon2.androidtv.R;

public class VideoItemDetailActivity extends Activity implements View.OnClickListener{

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_item_detail);

        btn = findViewById(R.id.visit_full_profile);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(VideoItemDetailActivity.this, ProductActivity.class);
        startActivity(i);
    }
}
