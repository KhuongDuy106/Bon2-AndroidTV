package bon2.androidtv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import bon2.androidtv.R;

import bon2.androidtv.helper.SignInDialog;
import bon2.androidtv.helper.SignUpDialog;

public class SignActivity extends Activity {

    SignInDialog signInDlg;
    SignUpDialog signUpDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }

    public void signInDlg(View v) {
        signInDlg = new SignInDialog(SignActivity.this);
        signInDlg.show();
    }

    public void signUpDlg(View v) {
        signUpDlg = new SignUpDialog(SignActivity.this);
        signUpDlg.show();
    }
}
