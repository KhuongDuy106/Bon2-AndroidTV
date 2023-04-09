package bon2.androidtv.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bon2.androidtv.R;

import bon2.androidtv.activity.HomeActivity;
import bon2.androidtv.activity.VideoActivity;
import bon2.androidtv.helper.RoundedImageView;
import bon2.androidtv.utils.Utils;

public class MyfeedFragment extends Fragment {

    private static int COUNT = 21;
    LinearLayout layout_myfeed;
    List<Integer> ids;
    public RelativeLayout rl;
    HorizontalScrollView hsv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_myfeed, container, false);

        layout_myfeed = layout.findViewById(R.id.layout_myfeed);

        hsv = layout.findViewById(R.id.list_scrollview);
        rl = layout.findViewById(R.id.list);
        ids = new ArrayList<>(COUNT);

        for(int i=0 ; i<COUNT ; i++) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_one, null);
            TextView title1 = v.findViewById(R.id.item_title1);
            title1.setText("My feed text" + i);
            TextView title2 = v.findViewById(R.id.item_title2);

            Integer id = View.generateViewId();
            v.setId(id);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(10,10,10,10);

            if( i == 0 ) {
                p.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            else if( i%9 == 0 ) {
                p.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
            }
            else if( i%9 == 1 ) {
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-1));
            }
            else if( i%9 == 2 ) {
                p.addRule(RelativeLayout.BELOW, ids.get(i-2));
                if( i > 2) p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
                int width = Utils.convertDpToPixel(getContext(), 164) * 2 + 20;
                int height = Utils.convertDpToPixel(getContext(), 114) * 2 + 20;
                p.width = width;
                p.height = height;
                ImageView riv = v.findViewById(R.id.item_logo);
                riv.getLayoutParams().width = width;
                riv.getLayoutParams().height = height;
                riv.requestLayout();

                title1.getLayoutParams().width = width;
                title1.requestLayout();
                title2.getLayoutParams().width = width;
                title2.requestLayout();
            }
            else if( i%9 == 3 ) {
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-2));
            }
            else if( i%9 == 4 ) {
                p.addRule(RelativeLayout.BELOW, ids.get(i-1));
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-2));
            }
            else if( i%9 == 5 ) {
                p.addRule(RelativeLayout.BELOW, ids.get(i-1));
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
            }
            else if( i%9 == 6 ) {
                p.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
            }
            else if( i%9 == 7 ) {
                p.addRule(RelativeLayout.BELOW, ids.get(i-1));
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
            }
            else if( i%9 == 8 ) {
                p.addRule(RelativeLayout.BELOW, ids.get(i-1));
                p.addRule(RelativeLayout.RIGHT_OF, ids.get(i-3));
            }
/*
            if(i == 0 || i == 2)
                v.setNextFocusLeftId(id);
*/
            v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus)
                    {
                        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.03f, 1.0f);
                        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.03f, 1.0f);
                        AnimatorSet set = new AnimatorSet();
                        set.play(scaleX).with(scaleY);
                        set.start();
                        v.findViewById(R.id.item_title2).setSelected(false);
                    }
                    else {
                        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.03f);
                        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.03f);
                        AnimatorSet set = new AnimatorSet();
                        set.play(scaleX).with(scaleY);
                        set.start();
                        v.findViewById(R.id.item_title2).setSelected(true);
                    }
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getContext(), VideoActivity.class);
                    getContext().startActivity(i);
                }
            });

            v.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCodeOfDPAD(event))
                        return true;

                    View focusView = rl.getFocusedChild();
                    if (focusView == null) {
                        return false;
                    } else {
                        int dy = 0;
                        int dx = 0;
                        if (rl.getChildCount() > 0) {
                            View firstView = rl.getChildAt(0);
                            dy = firstView.getHeight();
                            dx = firstView.getWidth();
                        }

                        switch (event.getKeyCode()) {
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                return findNextFocusView(focusView, new Point(dx, 0), View.FOCUS_RIGHT);
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                return findNextFocusView(focusView, new Point(-dx, 0), View.FOCUS_LEFT);
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                return findNextFocusView(focusView, new Point(0, dy), View.FOCUS_DOWN);
                            case KeyEvent.KEYCODE_DPAD_UP:
                                return findNextFocusView(focusView, new Point(0, -dy), View.FOCUS_UP);
                        }
                    }
                    return false;
                }
            });

            v.setLayoutParams(p);
            rl.addView(v);
            ids.add(id);
        }
        rl.requestFocus();

        return layout;
    }

    private boolean keyCodeOfDPAD(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP;
    }

    private boolean findNextFocusView(View focusView, Point vector, int direction) {
        View nextFocus = FocusFinder.getInstance().findNextFocus(rl, focusView, direction);
        if (nextFocus != null) {
            nextFocus.requestFocus();
        } else {
            hsv.smoothScrollBy(vector.x, vector.y);
        }
        return true;
    }
}
