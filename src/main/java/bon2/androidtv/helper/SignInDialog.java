package bon2.androidtv.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bon2.androidtv.activity.HomeActivity;

import bon2.androidtv.R;

public class SignInDialog extends Dialog {

    public Activity activity;
    public Dialog dlg;
    public Button signin;
    EditText userName, password;

    public SignInDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_signin_dialog);
        signin = findViewById(R.id.signin_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_username = (userName = findViewById(R.id.et_username)).getText().toString();
                String str_password = (password = findViewById(R.id.et_password)).getText().toString();

                if( str_username.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "User name is required", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }
                if( str_password.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "Password is required", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                dismiss();

                Intent i = new Intent(activity, HomeActivity.class);
                activity.startActivity(i);

                activity.finish();
            }
        });
    }
}
