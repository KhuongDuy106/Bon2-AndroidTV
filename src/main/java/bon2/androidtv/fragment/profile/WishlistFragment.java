package bon2.androidtv.fragment.profile;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import bon2.androidtv.R;
import bon2.androidtv.activity.VideoActivity;
import bon2.androidtv.adapter.DefaultItemDecoration;
import bon2.androidtv.adapter.ProfileWishlistAdapter;
import bon2.androidtv.helper.RoundedImageView;
import bon2.androidtv.utils.Utils;
import bon2.androidtv.view.TVRecyclerView;
import bon2.androidtv.view.TVRecyclerViewWishlist;

public class WishlistFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frag_profile_wishlist, container, false);

        container = layout.findViewById(R.id.container);

        TVRecyclerViewWishlist rv = new TVRecyclerViewWishlist(getContext());
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.LEFT;
        rv.setLayoutParams(params1);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 8, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(new ProfileWishlistAdapter(this.getContext()));
        rv.addItemDecoration(new DefaultItemDecoration(35,5, 5, 25));
        container.addView(rv);

        return layout;
    }

}
