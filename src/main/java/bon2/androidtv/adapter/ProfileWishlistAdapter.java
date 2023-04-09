package bon2.androidtv.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.activity.ProductActivity;
import bon2.androidtv.helper.RoundedImageView;
import bon2.androidtv.view.TVRecyclerViewWishlist;

public class ProfileWishlistAdapter extends TVRecyclerViewWishlistAdapter<TVRecyclerViewWishlistAdapter.ViewHolder> {
    private static final String TAG = "VideosAdapter";

    Context mContext;

    public ProfileWishlistAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.e(TAG, "onItemClick:" + pos);
            }
        });
    }

    @Override
    public TVRecyclerViewWishlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_profile_wishlist, parent, false);
        return new VH(mContext, view);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    protected void focusOut(View v) {
        ((RoundedImageView)v).setBorderColor(Color.parseColor("#0000ff00"));
    }

    protected void focusIn(View v) {
        ((RoundedImageView)v).setBorderColor(Color.parseColor("#ff00ff00"));
    }

    @Override
    protected void onDataBinding(TVRecyclerViewWishlistAdapter.ViewHolder holder, int position) {
        if (holder instanceof VH) {
            String str = ((VH) holder).name.getText().toString();
            ((VH) holder).name.setText(str + " " + position);

            ((VH) holder).logo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    ViewGroup parent = (ViewGroup) v.getParent();
                    if (hasFocus) {
                        focusIn(v);
                        if(parent != null)
                            parent.findViewById(R.id.item_name).setSelected(true);
                    } else {
                        focusOut(v);
                        if(parent != null)
                            parent.findViewById(R.id.item_name).setSelected(false);
                    }
                }
            });

            ((VH) holder).logo.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCodeOfDPAD(event))
                        return true;

                    View focusView = v;
                    if (focusView == null) {
                        return false;
                    } else {
                        int dy = 0;
                        int dx = 0;

                        LinearLayout item = (LinearLayout)v.getParent();
                        TVRecyclerViewWishlist rcview = (TVRecyclerViewWishlist)item.getParent();
                        View firstView = rcview.getChildAt(0);
                        dy = firstView.getHeight();
                        dx = firstView.getWidth();

                        switch (event.getKeyCode()) {
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                return findLogoNextFocusView(focusView, new Point(dx, 0), View.FOCUS_RIGHT);
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                return findLogoNextFocusView(focusView, new Point(-dx, 0), View.FOCUS_LEFT);
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                //return findLogoNextFocusView(focusView, new Point(0, dy), View.FOCUS_DOWN);
                            case KeyEvent.KEYCODE_DPAD_UP:
                                //return findLogoNextFocusView(focusView, new Point(0, -dy), View.FOCUS_UP);
                        }
                    }
                    return false;
                }
            });

            ((VH) holder).queue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        focusIn(v);
                    } else {
                        focusOut(v);
                    }
                }
            });
            ((VH) holder).queue.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCodeOfDPAD(event))
                        return true;

                    View focusView = v;
                    if (focusView == null) {
                        return false;
                    } else {
                        int dy = 0;
                        int dx = 0;

                        LinearLayout hl = (LinearLayout)v.getParent();
                        LinearLayout item = (LinearLayout)hl.getParent();
                        TVRecyclerViewWishlist rcview = (TVRecyclerViewWishlist)item.getParent();
                        View firstView = rcview.getChildAt(0);
                        dy = firstView.getHeight();
                        dx = firstView.getWidth();

                        switch (event.getKeyCode()) {
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                return findQueueNextFocusView(focusView, new Point(dx, 0), View.FOCUS_RIGHT);
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                return findQueueNextFocusView(focusView, new Point(-dx, 0), View.FOCUS_LEFT);
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                //return findQueueNextFocusView(focusView, new Point(0, dy), View.FOCUS_DOWN);
                            case KeyEvent.KEYCODE_DPAD_UP:
                                //return findQueueNextFocusView(focusView, new Point(0, -dy), View.FOCUS_UP);
                        }
                    }
                    return false;
                }
            });
        }
    }

    private boolean keyCodeOfDPAD(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN
                || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP;
    }

    private boolean findLogoNextFocusView(View focusView, Point vector, int direction) {
        LinearLayout item = (LinearLayout)focusView.getParent();
        TVRecyclerViewWishlist rcview = (TVRecyclerViewWishlist)item.getParent();
        LinearLayout container = (LinearLayout) rcview.getParent();
        ScrollView scroll = (ScrollView) container.getParent();
        View nextFocus = FocusFinder.getInstance().findNextFocus(rcview, focusView, direction);
        if (nextFocus != null) {
            nextFocus.requestFocus();
        }
        else {
            scroll.smoothScrollBy(vector.x, vector.y);
        }
        return true;
    }

    private boolean findQueueNextFocusView(View focusView, Point vector, int direction) {
        LinearLayout hl = (LinearLayout)focusView.getParent();
        LinearLayout item = (LinearLayout)hl.getParent();
        TVRecyclerViewWishlist rcview = (TVRecyclerViewWishlist)item.getParent();
        LinearLayout container = (LinearLayout) rcview.getParent();
        ScrollView scroll = (ScrollView) container.getParent();
        View nextFocus = FocusFinder.getInstance().findNextFocus(rcview, focusView, direction);
        if (nextFocus != null) {
            if(direction == View.FOCUS_LEFT || direction == View.FOCUS_RIGHT) {
                LinearLayout item1 = (LinearLayout)nextFocus.getParent();
                RoundedImageView riv = item1.findViewById(R.id.wish_queue);
                riv.requestFocus();
            }
            else
                nextFocus.requestFocus();
        } else {
            scroll.smoothScrollBy(vector.x, vector.y);
        }

        return true;
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class VH extends TVRecyclerViewWishlistAdapter.ViewHolder {
        RoundedImageView logo;
        RoundedImageView queue;
        TextView name;

        public VH(Context context, View itemView) {
            super(context, itemView);

            logo = itemView.findViewById(R.id.item_logo);
            queue = itemView.findViewById(R.id.wish_queue);
            name = itemView.findViewById(R.id.item_name);
        }
    }

}
