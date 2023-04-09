package bon2.androidtv.fragment.profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import bon2.androidtv.R;
import bon2.androidtv.activity.SignActivity;

public class EditProfileFragment extends Fragment implements View.OnClickListener{

    Button link_to_artist, link_your_business, edit_profile, reset_password, log_out;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frag_profile_edit, container, false);

        link_to_artist = layout.findViewById(R.id.link_to_artist);
        link_to_artist.setOnClickListener(this);
        link_your_business = layout.findViewById(R.id.link_your_business);
        link_your_business.setOnClickListener(this);
        edit_profile = layout.findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(this);
        reset_password = layout.findViewById(R.id.reset_password);
        reset_password.setOnClickListener(this);
        log_out = layout.findViewById(R.id.log_out);
        log_out.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id) {
            case R.id.link_to_artist:
            case R.id.link_your_business:
                Context ct = this.getContext();
                Toast toast = new Toast(ct);
                toast.setDuration(Toast.LENGTH_SHORT);
                LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.layout_toast, null);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
                toast.show();
                break;
            case R.id.edit_profile:
                Intent i = new Intent(this.getActivity(), SignActivity.class);
                startActivity(i);
                break;
            case R.id.reset_password:
                break;
            case R.id.log_out:
                Intent ii = new Intent(this.getActivity(), SignActivity.class);
                startActivity(ii);

                this.getActivity().finish();
                break;
        }
    }
}
