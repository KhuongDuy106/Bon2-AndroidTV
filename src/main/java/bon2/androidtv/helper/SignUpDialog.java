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

public class SignUpDialog extends Dialog {

    public Activity activity;
    public Dialog dlg;
    public Button signup;
    EditText firstName, lastName, userName, email, password;

    public SignUpDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_signup_dialog);
        signup = findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_firstname = (firstName = findViewById(R.id.et_firstName)).getText().toString();
                String str_lastname = (lastName = findViewById(R.id.et_lastName)).getText().toString();
                String str_username = (userName = findViewById(R.id.et_username_signup)).getText().toString();
                String str_email = (email = findViewById(R.id.et_email)).getText().toString();
                String str_password = (password = findViewById(R.id.et_password_signup)).getText().toString();

                if( str_firstname.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "First name is required", Toast.LENGTH_SHORT).show();
                    firstName.requestFocus();
                    return;
                }
                if( str_lastname.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "User name is required", Toast.LENGTH_SHORT).show();
                    lastName.requestFocus();
                    return;
                }
                if( str_username.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "User name is required", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }
                if( str_email.equals("") ) {
                    Toast.makeText(activity.getBaseContext(), "Email is required", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
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
