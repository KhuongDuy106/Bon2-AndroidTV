package bon2.androidtv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bon2.androidtv.R;

public class PeopleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }

    public void onClickFollowUnfollow(View v) {
        Button btn = (Button)v;
        String str = btn.getText().toString();
        if( str.equals("Follow") ) {
            btn.setText("Unfollow");
        }
        else {
            btn.setText("Follow");
        }
    }
}
