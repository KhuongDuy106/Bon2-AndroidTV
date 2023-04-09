package bon2.androidtv;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;

import bon2.androidtv.activity.SignActivity;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgressDialog(MainActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressDialog(MainActivity.this);

                Intent i = new Intent(MainActivity.this, SignActivity.class);
                startActivity(i);

                MainActivity.this.finish();
            }
        }, 1000);
    }

    void showProgressDialog(Context c) {
        if (progress == null) {
            progress = new ProgressDialog(c);
        }
        try {
            progress.show();
            progress.setCancelable(false);
            progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progress.setContentView(R.layout.layout_progress_dilog);
        } catch (Exception e) {}
    }

    void hideProgressDialog(Context c) {
        if (progress != null && progress.isShowing())
            progress.dismiss();
    }
}

