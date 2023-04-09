package bon2.androidtv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import bon2.androidtv.R;
import bon2.androidtv.fragment.profile.EditProfileFragment;
import bon2.androidtv.fragment.profile.FollowersFragment;
import bon2.androidtv.fragment.profile.FollowingsFragment;
import bon2.androidtv.fragment.profile.OverviewFragment;
import bon2.androidtv.fragment.profile.PostsFragment;
import bon2.androidtv.fragment.profile.ProfileStationsFragment;
import bon2.androidtv.fragment.profile.WishlistFragment;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    public Button posts, messages, wishlist, stations, followings, followers, profile, overview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_profile, container, false);

        posts = layout.findViewById(R.id.profile_btn_posts);
        posts.requestFocus();
        posts.setOnClickListener(this);
        messages = layout.findViewById(R.id.profile_btn_messages);
        messages.setOnClickListener(this);
        wishlist = layout.findViewById(R.id.profile_btn_wishlist);
        wishlist.setOnClickListener(this);
        stations = layout.findViewById(R.id.profile_btn_stations);
        stations.setOnClickListener(this);
        followings = layout.findViewById(R.id.profile_btn_followings);
        followings.setOnClickListener(this);
        followers = layout.findViewById(R.id.profile_btn_followers);
        followers.setOnClickListener(this);
        profile = layout.findViewById(R.id.profile_btn_profile);
        profile.setOnClickListener(this);
        overview = layout.findViewById(R.id.profile_btn_overview);
        overview.setOnClickListener(this);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        PostsFragment posts = new PostsFragment();
        ft.replace(R.id.profile_frame_container, posts, "Profile Posts Fragment");
        ft.commit();

        return layout;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setBackgroundAll();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        switch(id) {
            case R.id.profile_btn_posts:
                posts.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                PostsFragment posts = new PostsFragment();
                ft.replace(R.id.profile_frame_container, posts, "Profile Posts Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_messages:
                messages.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                Context ct = this.getContext();
                Toast toast = new Toast(ct);
                toast.setDuration(Toast.LENGTH_SHORT);

                LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.layout_toast, null);
                toast.setView(view);
                toast.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
                toast.show();
                break;
            case R.id.profile_btn_wishlist:
                wishlist.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                WishlistFragment wishlist = new WishlistFragment();
                ft.replace(R.id.profile_frame_container, wishlist, "Profile Wishlist Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_stations:
                stations.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                ProfileStationsFragment stations = new ProfileStationsFragment();
                ft.replace(R.id.profile_frame_container, stations, "Profile Stations Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_followings:
                followings.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                FollowingsFragment followings = new FollowingsFragment();
                ft.replace(R.id.profile_frame_container, followings, "Profile Followings Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_followers:
                followers.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                FollowersFragment followers = new FollowersFragment();
                ft.replace(R.id.profile_frame_container, followers, "Profile Folllowers Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_profile:
                profile.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                EditProfileFragment epf = new EditProfileFragment();
                ft.replace(R.id.profile_frame_container, epf, "Edit Profile Fragment").addToBackStack(null).commit();
                break;
            case R.id.profile_btn_overview:
                overview.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                OverviewFragment overview = new OverviewFragment();
                ft.replace(R.id.profile_frame_container, overview, "Profile Overview Fragment").addToBackStack(null).commit();
                break;
        }
        //Toast.makeText(this.getContext(), ((Button)v).getText(), Toast.LENGTH_SHORT).show();
    }

    public void setBackgroundAll() {
        posts.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        messages.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        wishlist.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        stations.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        followings.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        followers.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        profile.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        overview.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
    }
}
