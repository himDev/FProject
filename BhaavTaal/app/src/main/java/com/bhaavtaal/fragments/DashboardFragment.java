package com.bhaavtaal.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bhaavtaal.R;
import com.bhaavtaal.adapters.HomeGridAdapter;
import com.bhaavtaal.adapters.ViewPagerAdapter;
import com.bhaavtaal.dashboard.DividerItemDecoration;
import com.bhaavtaal.models.Home_grid_list;
import com.bhaavtaal.models.Home_slider_list;
import com.bhaavtaal.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class DashboardFragment extends Fragment implements ViewPager.OnPageChangeListener {


    Activity activity;
    private ViewPager viewPager;

    private int[] mImageResources = {
            R.mipmap.tomato,
            R.mipmap.carrot,
            R.mipmap.spice,
            R.mipmap.pumpkin,
            R.mipmap.banana
    };

    private List<Home_grid_list> homeGridList = new ArrayList<>();
    private List<Home_slider_list> homeSliderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HomeGridAdapter homeGrideadapter;

    private String[] mVegsImageResources = {
            "Tomato 80.00 Rs",
            "Carrots 80.00 Rs",
            "Chills 80.00 Rs",
            "Pumpkin 80.00 Rs",
            "Banana 80.00 Rs"
    };

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    View view;

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    Timer timer;
    static int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initViews();
        prepareGrideData();
        setRecyclerViewWithdata();
        setTopViewPagerAdapter();
        setUiPageViewController();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //Top View Pager
    private void setTopViewPagerAdapter() {
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        pageSwitcher(3);
    }

    //Recycler view
    private void setRecyclerViewWithdata() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        homeGrideadapter = new HomeGridAdapter(activity, homeGridList);

        RecyclerView.LayoutManager lm = new GridLayoutManager(activity, 2);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeGrideadapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new DashboardFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Home_grid_list homeGrid = homeGridList.get(position);
                Toast.makeText(activity, homeGrid.item_image + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(activity);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    private void initViews() {
        viewPager = (ViewPager) view.findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
//        Resources res = getResources();
//        TypedArray colors = res.obtainTypedArray(R.array.array_dot_active);
//        mAdapter = new ViewPagerAdapter(activity, mImageResources, mVegsImageResources, colors);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    // this is an inner class...
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.

            Activity activity = getActivity();
            if (activity != null) {

                // etc ...

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        if (page > 6) {
                            page = 1;
                        }
                        viewPager.setCurrentItem(page++);
                    }
                });
            }
        }
    }

    private void prepareGrideData() {

        try {
            JSONObject grid_list_jobj = new JSONObject(Util.home_grid_json);
            JSONArray jsonHomeGridArray = grid_list_jobj.getJSONArray("home_grid_list");
            JSONArray jsonSliderArray = grid_list_jobj.getJSONArray("slider_items");

            for (int i = 0; i < jsonSliderArray.length(); i++) {
//                Movie movie = new Movie(jsonArray.get", "60 Rs", "2015");
                Home_slider_list home_slider_list = new Home_slider_list();
                home_slider_list.title = jsonSliderArray.getJSONObject(i).getString("title");
                home_slider_list.desc = jsonSliderArray.getJSONObject(i).getString("desc");

                homeSliderList.add(home_slider_list);
            }

            mAdapter = new ViewPagerAdapter(getActivity(), homeSliderList);

            for (int i = 0; i < jsonHomeGridArray.length(); i++) {
//                Movie movie = new Movie(jsonArray.get", "60 Rs", "2015");
                Home_grid_list home_grid_list = new Home_grid_list();
                home_grid_list.item_name = jsonHomeGridArray.getJSONObject(i).getString("item_name");
                home_grid_list.desc = jsonHomeGridArray.getJSONObject(i).getString("desc");
                home_grid_list.item_image = jsonHomeGridArray.getJSONObject(i).getString("item_image");
                home_grid_list.distance_from_current = jsonHomeGridArray.getJSONObject(i).getString("distance_from_current");
                home_grid_list.price = jsonHomeGridArray.getJSONObject(i).getString("price");

                homeGridList.add(home_grid_list);
            }
        } catch (Exception e) {
            Log.d("", "Exception: " + e);
        }
//        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
