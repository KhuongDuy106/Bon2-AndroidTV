package bon2.androidtv.fragment.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import bon2.androidtv.R;
import bon2.androidtv.adapter.DefaultItemDecoration;
import bon2.androidtv.adapter.ProfileStationsAdapter;
import bon2.androidtv.view.TVRecyclerView;

public class ProfileStationsFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frag_profile_stations, container, false);

        container = layout.findViewById(R.id.container);

        TVRecyclerView rv = new TVRecyclerView(getContext());
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.LEFT;
        rv.setLayoutParams(params1);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 8, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(new ProfileStationsAdapter(this.getContext()));
        rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
        container.addView(rv);

        return layout;
    }

}
