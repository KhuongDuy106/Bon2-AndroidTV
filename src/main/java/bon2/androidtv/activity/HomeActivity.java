package bon2.androidtv.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import bon2.androidtv.R;
import bon2.androidtv.fragment.DiscoverFragment;
import bon2.androidtv.fragment.FeaturedFragment;
import bon2.androidtv.fragment.MyfeedFragment;
import bon2.androidtv.fragment.ProfileFragment;
import bon2.androidtv.fragment.SearchFragment;
import bon2.androidtv.fragment.StationsFragment;
import bon2.androidtv.utils.Utils;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    public static String selected_fragment = "My Feed";

    private boolean menu_show_state = false;
    private ExpandableRelativeLayout exp_menu;
    private LinearLayout icon_my_feed, icon_discover, icon_featured, icon_stations, icon_search, icon_cart, icon_profile;
    private LinearLayout my_feed, discover, featured, stations, search, profile;
    private LinearLayout selected_icon_menu, selected_menu;
    private LinearLayout cur_icon_menu, cur_menu;
    
    public int old_selected_id;

    private FragmentTransaction ft;

    MyfeedFragment my_feed_frag;
    DiscoverFragment discover_frag;
    FeaturedFragment featured_frag;
    StationsFragment stations_frag;
    SearchFragment search_frag;
    ProfileFragment profile_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        exp_menu = findViewById(R.id.exp_menu);

        icon_my_feed = findViewById(R.id.menu_icon_myfeed);
        icon_discover = findViewById(R.id.menu_icon_discover);
        icon_featured = findViewById(R.id.menu_icon_featured);
        icon_stations = findViewById(R.id.menu_icon_stations);
        icon_search = findViewById(R.id.menu_icon_search);
        icon_profile = findViewById(R.id.menu_icon_profile);

        my_feed = findViewById(R.id.menu_myfeed);
        discover = findViewById(R.id.menu_discover);
        featured = findViewById(R.id.menu_featured);
        stations = findViewById(R.id.menu_stations);
        search = findViewById(R.id.menu_search);
        profile = findViewById(R.id.menu_profile);

        my_feed.setOnClickListener(this);
        my_feed.setOnFocusChangeListener(this);

        discover.setOnClickListener(this);
        discover.setOnFocusChangeListener(this);

        featured.setOnClickListener(this);
        featured.setOnFocusChangeListener(this);

        stations.setOnClickListener(this);
        stations.setOnFocusChangeListener(this);

        search.setOnClickListener(this);
        search.setOnFocusChangeListener(this);

        profile.setOnClickListener(this);
        profile.setOnFocusChangeListener(this);

        selected_icon_menu = icon_my_feed;
        selected_menu = my_feed;
        old_selected_id = my_feed.getId();

        ft = HomeActivity.this.getSupportFragmentManager().beginTransaction();
        my_feed_frag = new MyfeedFragment();
        ft.add(R.id.frame_container, my_feed_frag, "Myfeed Fragment");
        ft.commit();

        discover_frag = new DiscoverFragment();
        featured_frag = new FeaturedFragment();
        stations_frag = new StationsFragment();
        search_frag = new SearchFragment();
        profile_frag = new ProfileFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                if( menu_show_state == false ) {
                    exp_menu.move(Utils.convertDpToPixel(this, (int)getResources().getDimension(R.dimen.menu_width)));
                    exp_menu.moveChild(1);
                    menu_show_state = true;
                    cur_icon_menu = selected_icon_menu;
                    cur_menu = selected_menu;
                    selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
                    selected_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
                    selected_menu.requestFocus();
                }
                return false;
            case KeyEvent.KEYCODE_BACK:
            {
                if( menu_show_state == false ) {
                    exp_menu.move(Utils.convertDpToPixel(this, (int)getResources().getDimension(R.dimen.menu_width)));
                    exp_menu.moveChild(1);
                    menu_show_state = true;
                    cur_icon_menu = selected_icon_menu;
                    cur_menu = selected_menu;
                    selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
                    selected_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
                    selected_menu.requestFocus();
                }
                else {
                    this.finish();
                }
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if( menu_show_state == false ) {
            return;
        }

        exp_menu.moveChild(0);
        exp_menu.move(Utils.convertDpToPixel(this, (int)getResources().getDimension(R.dimen.menu_icon_width)));
        menu_show_state = false;

        ft = HomeActivity.this.getSupportFragmentManager().beginTransaction();

        int id = v.getId();

        selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.transparent));
        selected_menu.setBackgroundColor(getResources().getColor(R.color.transparent));

        if( id == R.id.menu_myfeed ) {
            selected_fragment = "My Feed";
            selected_icon_menu = icon_my_feed;
            selected_menu = my_feed;
            ft.replace(R.id.frame_container, my_feed_frag, "Myfeed Fragment").addToBackStack(null).commit();
            my_feed_frag.rl.requestFocus();
        }
        else if( id == R.id.menu_discover ) {
            selected_fragment = "Discover";
            selected_icon_menu = icon_discover;
            selected_menu = discover;
            ft.replace(R.id.frame_container, discover_frag, "Discover Fragment").addToBackStack(null).commit();
            if( id == old_selected_id ) discover_frag.people_list.requestFocus();
        }
        else if( id == R.id.menu_featured ) {
            selected_fragment = "Featured";
            selected_icon_menu = icon_featured;
            selected_menu = featured;
            ft.replace(R.id.frame_container, featured_frag, "Featured Fragment").addToBackStack(null).commit();
            if( id == old_selected_id ) featured_frag.event_list.requestFocus();
        }
        else if( id == R.id.menu_stations ) {
            selected_fragment = "Stations";
            selected_icon_menu = icon_stations;
            selected_menu = stations;
            ft.replace(R.id.frame_container, stations_frag, "Stations Fragment").addToBackStack(null).commit();
            if( id == old_selected_id ) stations_frag.bon2tv.requestFocus();
        }
        else if( id == R.id.menu_search ) {
            selected_fragment = "Search";
            selected_icon_menu = icon_search;
            selected_menu = search;
            ft.replace(R.id.frame_container, search_frag, "Search Fragment").addToBackStack(null).commit();
            if( id == old_selected_id ) search_frag.all.requestFocus();
        }
        else if( id == R.id.menu_profile ) {
            selected_fragment = "Profile";
            selected_icon_menu = icon_profile;
            selected_menu = profile;
            ft.replace(R.id.frame_container, profile_frag, "Profile Fragment").addToBackStack(null).commit();
            if( id == old_selected_id ) profile_frag.posts.requestFocus();
        }

        old_selected_id = id;
        selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
        selected_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
        if(selected_menu != cur_menu && cur_icon_menu != null ) {
            cur_icon_menu.setBackgroundColor(getResources().getColor(R.color.transparent));
            cur_menu.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        cur_icon_menu = cur_menu = null;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if( menu_show_state == false ) {
            return;
        }

        if( hasFocus ) {
            int id = view.getId();

            selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.transparent));
            selected_menu.setBackgroundColor(getResources().getColor(R.color.transparent));

            if (id == R.id.menu_myfeed) {
                selected_menu = my_feed;
                selected_icon_menu = icon_my_feed;
            }
            else if (id == R.id.menu_discover) {
                selected_menu = discover;
                selected_icon_menu = icon_discover;
            }
            else if (id == R.id.menu_featured) {
                selected_menu = featured;
                selected_icon_menu = icon_featured;
            }
            else if (id == R.id.menu_stations) {
                selected_menu = stations;
                selected_icon_menu = icon_stations;
            }
            else if (id == R.id.menu_search) {
                selected_menu = search;
                selected_icon_menu = icon_search;
            }
            else if (id == R.id.menu_profile) {
                selected_menu = profile;
                selected_icon_menu = icon_profile;
            }

            selected_icon_menu.setBackgroundColor(getResources().getColor(R.color.menu_focused));
            selected_menu.setBackgroundColor(getResources().getColor(R.color.menu_focused));
            if (cur_icon_menu != null) {
                cur_icon_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
                cur_menu.setBackgroundColor(getResources().getColor(R.color.menu_selected));
            }
        }
    }
}
