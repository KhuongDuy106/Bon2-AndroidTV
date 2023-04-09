package bon2.androidtv.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.adapter.DefaultItemDecoration;
import bon2.androidtv.adapter.PeopleRecyclerViewAdapter;
import bon2.androidtv.adapter.ProductsRecyclerViewAdapter;
import bon2.androidtv.adapter.VideosRecyclerViewAdapter;
import bon2.androidtv.view.TVRecyclerView;

public class SearchFragment extends Fragment implements View.OnClickListener {

    LinearLayout search_result;
    public Button all, videos, posts, people, products, places, hashtags;
    String[] category_title = {"Videos", "Posts", "People", "Products", "Places", "HashTags"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);

        all = layout.findViewById(R.id.search_all);
        all.requestFocus();
        all.setOnClickListener(this);
        videos = layout.findViewById(R.id.search_videos);
        videos.setOnClickListener(this);
        posts = layout.findViewById(R.id.search_posts);
        posts.setOnClickListener(this);
        people = layout.findViewById(R.id.search_people);
        people.setOnClickListener(this);
        products = layout.findViewById(R.id.search_products);
        products.setOnClickListener(this);
        places = layout.findViewById(R.id.search_places);
        places.setOnClickListener(this);
        hashtags = layout.findViewById(R.id.search_hashtags);
        hashtags.setOnClickListener(this);

        search_result = layout.findViewById(R.id.ll_search_result);
        setupResult(R.id.search_all);
        return layout;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setBackgroundAll();
        switch(id) {
            case R.id.search_all:
                setupResult(R.id.search_all);
                all.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_videos:
                setupResult(R.id.search_videos);
                videos.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_posts:
                setupResult(R.id.search_posts);
                posts.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_people:
                setupResult(R.id.search_people);
                people.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_products:
                setupResult(R.id.search_products);
                products.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_places:
                setupResult(R.id.search_places);
                places.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
            case R.id.search_hashtags:
                setupResult(R.id.search_hashtags);
                hashtags.setBackground(getResources().getDrawable(R.drawable.btn_background_selected));
                break;
        }
        //Toast.makeText(this.getContext(), ((Button)v).getText(), Toast.LENGTH_SHORT).show();
    }

    public void setBackgroundAll() {
        all.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        videos.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        posts.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        people.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        products.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        places.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
        hashtags.setBackground(getResources().getDrawable(R.drawable.btn_background_normal));
    }

    public void setupResult(int id) {
        search_result.removeAllViews();

        if ( id == R.id.search_all ) {
            for (int i=0 ; i<category_title.length ; i++) {
                TextView textView = new TextView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 20);
                textView.setLayoutParams(params);
                textView.setText(category_title[i]);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setTextSize(15);
                textView.setGravity(Gravity.LEFT);
                textView.setAllCaps(true);
                textView.setFocusable(false);
                search_result.addView(textView);

                TVRecyclerView rv = new TVRecyclerView(getContext());
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params1.gravity = Gravity.LEFT;
                params1.setMargins(0, 0, 0, 30);
                rv.setLayoutParams(params1);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
                rv.setLayoutManager(manager);
                rv.addItemDecoration(new DefaultItemDecoration(20,5, 0, 10));

                switch(i) {
                    case 0: //Videos
                        rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
                        break;
                    case 1: //Posts
                        rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
                        break;
                    case 2: //People
                        rv.setAdapter(new PeopleRecyclerViewAdapter(this.getContext()));
                        break;
                    case 3: //Products
                        rv.setAdapter(new ProductsRecyclerViewAdapter(this.getContext()));
                        break;
                    case 4: //Places
                        rv.setAdapter(new ProductsRecyclerViewAdapter(this.getContext()));
                        break;
                    case 5: //HashTags
                        rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
                        break;
                }
                search_result.addView(rv);
            }
        }
        else if( id == R.id.search_videos ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 5, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
        else if( id == R.id.search_posts ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 5, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
        else if( id == R.id.search_people ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 7, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new PeopleRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
        else if( id == R.id.search_products ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 7, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new ProductsRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
        else if( id == R.id.search_places ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 7, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new ProductsRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
        else if( id == R.id.search_hashtags ) {
            TVRecyclerView rv = new TVRecyclerView(getContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.gravity = Gravity.LEFT;
            rv.setLayoutParams(params1);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 5, GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(manager);
            rv.setAdapter(new VideosRecyclerViewAdapter(this.getContext()));
            rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
            search_result.addView(rv);
        }
    }
}
