package bon2.androidtv.fragment.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.utils.Utils;

public class PostsFragment extends Fragment {

    static ScrollView vscroll;
    static ViewGroup container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frag_profile_posts, container, false);

        vscroll = layout.findViewById(R.id.scroll);

        container = layout.findViewById(R.id.container);
        PostsFragment.container = container;
        View d1 = LayoutInflater.from(getContext()).inflate(R.layout.item_one_profile_posts_date, null);
        View i1 = LayoutInflater.from(getContext()).inflate(R.layout.item_one_profile_posts_item, null);
        LinearLayout item_container = i1.findViewById(R.id.item_container);
        item_container.setNextFocusUpId(R.id.profile_btn_posts);
        container.addView(d1);
        container.addView(i1);

        View d2 = LayoutInflater.from(getContext()).inflate(R.layout.item_one_profile_posts_date, null);
        d2.setPadding(0, Utils.convertDpToPixel(getContext(),10),0, 0);
        TextView t2 = d2.findViewById(R.id.day);
        t2.setText("03");
        View i2 = LayoutInflater.from(getContext()).inflate(R.layout.item_one_profile_posts_item, null);
        View i3 = LayoutInflater.from(getContext()).inflate(R.layout.item_one_profile_posts_item, null);

        ImageView iv2 = i2.findViewById(R.id.item_logo);
        iv2.setVisibility(View.VISIBLE);

        ImageView iv3 = i3.findViewById(R.id.item_logo);
        iv3.setVisibility(View.VISIBLE);
        iv3.setImageDrawable(getResources().getDrawable(R.drawable.item_place));

        container.addView(d2);
        container.addView(i2);
        container.addView(i3);

        return layout;
    }
}
