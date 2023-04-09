package bon2.androidtv.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.adapter.DefaultItemDecoration;
import bon2.androidtv.adapter.VideosRecyclerViewAdapter;
import bon2.androidtv.view.TVRecyclerView;

public class StationsFragment extends Fragment implements View.OnClickListener {

    LinearLayout scheduled_category, ll_items;
    public Button bon2tv, comedy, cooking, dance, diy_howto, family, fashion, health_beauty, just_pics, lifestyle,
            movie_trailers, music_videos, short_films, skit, sports, tv_teaser, travel;
    Button on_demand, scheduled;
    Button one_hour, four_hours, eight_hours, one_day, two_days, one_week;
    int selected_category_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stations, container, false);

        bon2tv = layout.findViewById(R.id.bon2tv);
        bon2tv.setOnClickListener(this);
        comedy = layout.findViewById(R.id.comedy);
        comedy.setOnClickListener(this);
        cooking = layout.findViewById(R.id.cooking);
        cooking.setOnClickListener(this);
        dance = layout.findViewById(R.id.dance);
        dance.setOnClickListener(this);
        diy_howto = layout.findViewById(R.id.diy_howto);
        diy_howto.setOnClickListener(this);
        family = layout.findViewById(R.id.family);
        family.setOnClickListener(this);
        fashion = layout.findViewById(R.id.fashion);
        fashion.setOnClickListener(this);
        health_beauty = layout.findViewById(R.id.health_beauty);
        health_beauty.setOnClickListener(this);
        just_pics = layout.findViewById(R.id.just_for_pics);
        just_pics.setOnClickListener(this);
        lifestyle = layout.findViewById(R.id.lifestyle);
        lifestyle.setOnClickListener(this);
        movie_trailers = layout.findViewById(R.id.movie_trailers);
        movie_trailers.setOnClickListener(this);
        music_videos = layout.findViewById(R.id.music_videos);
        music_videos.setOnClickListener(this);
        short_films = layout.findViewById(R.id.short_films);
        short_films.setOnClickListener(this);
        skit = layout.findViewById(R.id.skit);
        skit.setOnClickListener(this);
        sports = layout.findViewById(R.id.sports);
        sports.setOnClickListener(this);
        tv_teaser = layout.findViewById(R.id.tv_teaser);
        tv_teaser.setOnClickListener(this);
        travel = layout.findViewById(R.id.travel);
        travel.setOnClickListener(this);

        SubCategoryClickListener subCategoryClickListener = new SubCategoryClickListener();
        on_demand = layout.findViewById(R.id.on_demand);
        on_demand.setOnClickListener(subCategoryClickListener);
        scheduled = layout.findViewById(R.id.scheduled);
        scheduled.setOnClickListener(subCategoryClickListener);

        ScheduleCategoryClickListener scheduleCategoryClickListener = new ScheduleCategoryClickListener();
        one_hour = layout.findViewById(R.id.one_hour);
        one_hour.setOnClickListener(scheduleCategoryClickListener);
        four_hours = layout.findViewById(R.id.four_hours);
        four_hours.setOnClickListener(scheduleCategoryClickListener);
        eight_hours = layout.findViewById(R.id.eight_hours);
        eight_hours.setOnClickListener(scheduleCategoryClickListener);
        one_day = layout.findViewById(R.id.one_day);
        one_day.setOnClickListener(scheduleCategoryClickListener);
        two_days = layout.findViewById(R.id.two_days);
        two_days.setOnClickListener(scheduleCategoryClickListener);
        one_week = layout.findViewById(R.id.one_week);
        one_week.setOnClickListener(scheduleCategoryClickListener);
        
        bon2tv.requestFocus();
        scheduled_category = layout.findViewById(R.id.scheduled_category);
        ll_items = layout.findViewById(R.id.ll_items);

        selected_category_id = R.id.bon2tv;
        on_demand.callOnClick();
        return layout;
    }

    public class SubCategoryClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.on_demand:
                    on_demand.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    scheduled.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
                    scheduled_category.setVisibility(View.GONE);
                    setupResult(id);
                    break;
                case R.id.scheduled:
                    scheduled.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    on_demand.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
                    scheduled_category.setVisibility(View.VISIBLE);
                    one_hour.callOnClick();
                    break;
            }
        }
    }

    public class ScheduleCategoryClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setScheduleCategoryBackground();
            int id = v.getId();
            switch (id) {
                case R.id.one_hour:
                    one_hour.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
                case R.id.four_hours:
                    four_hours.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
                case R.id.eight_hours:
                    eight_hours.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
                case R.id.one_day:
                    one_day.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
                case R.id.two_days:
                    two_days.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
                case R.id.one_week:
                    one_week.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
                    break;
            }
            scheduleCategoryClicked(id);
        }
    }

    @Override
    public void onClick(View v) {
        selected_category_id = v.getId();
        setCategoryBackground();
        scheduled_category.setVisibility(View.GONE);
        switch(selected_category_id) {
            case R.id.bon2tv:
                bon2tv.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.comedy:
                comedy.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.cooking:
                cooking.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.dance:
                dance.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.diy_howto:
                diy_howto.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.family:
                family.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.fashion:
                fashion.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.health_beauty:
                health_beauty.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.just_for_pics:
                just_pics.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.lifestyle:
                lifestyle.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.movie_trailers:
                movie_trailers.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.music_videos:
                music_videos.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.short_films:
                short_films.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.skit:
                skit.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.sports:
                sports.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.tv_teaser:
                tv_teaser.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.travel:
                travel.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
        }
        setupResult(selected_category_id);
    }

    public void setCategoryBackground() {
        bon2tv.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        comedy.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        cooking.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        dance.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        diy_howto.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        family.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        fashion.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        health_beauty.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        just_pics.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        lifestyle.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        movie_trailers.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        music_videos.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        short_films.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        skit.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        sports.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        tv_teaser.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        travel.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));

        on_demand.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_selected));
        scheduled.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
    }

    public void setScheduleCategoryBackground() {
        one_hour.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
        four_hours.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
        eight_hours.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
        one_day.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
        two_days.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
        one_week.setBackground(getResources().getDrawable(R.drawable.btn_subcategory_normal));
    }
    
    public void setupResult(int id) {
        ll_items.removeAllViews();

        TVRecyclerView rv = new TVRecyclerView(getContext());
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.LEFT;
        rv.setLayoutParams(params1);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 5, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.addItemDecoration(new DefaultItemDecoration(5,5, 25, 25));
        rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));

        ll_items.addView(rv);
    }
    
    public void scheduleCategoryClicked(int id) {
        ll_items.removeAllViews();

        RelativeLayout rll = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rll.setLayoutParams(params);

        ImageView img = new ImageView(getContext());
        img.setImageDrawable(getResources().getDrawable(R.drawable.just_for_pics));
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img.setLayoutParams(params1);
        img.setAdjustViewBounds(true);
        rll.addView(img);

        TextView tv1 = new TextView(getContext());
        int tv1_id = View.generateViewId();
        tv1.setId(tv1_id);
        tv1.setText("Just for Pics");
        tv1.setTextSize(25);
        tv1.setTypeface(tv1.getTypeface(), Typeface.BOLD);
        tv1.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.setMargins(25,0,0,10);
        params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        tv1.setLayoutParams(params2);
        rll.addView(tv1);

        TextView tv2 = new TextView(getContext());
        tv2.setText("Be the first to partner with BON2 on Cooking station. Contact us at the.people@bon2.com");
        tv2.setTextSize(15);
        tv2.setTextColor(Color.parseColor("#ffffff"));
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.setMargins(25,0,0,0);
        params3.addRule(RelativeLayout.BELOW, tv1_id);
        tv2.setLayoutParams(params3);
        rll.addView(tv2);

        ll_items.addView(rll);
    }
}
