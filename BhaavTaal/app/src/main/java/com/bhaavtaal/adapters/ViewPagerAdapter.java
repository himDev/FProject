package com.bhaavtaal.adapters;

/**
 * Created by USER 8 on 23-Aug-16.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bhaavtaal.R;
import com.bhaavtaal.models.Home_slider_list;

import java.util.List;


/**
 * Created by Wasim on 11-06-2015.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    List<Home_slider_list> homeSliderList;

    public ViewPagerAdapter(Context context, List<Home_slider_list> homeSliderList) {
        this.mContext = context;
        this.homeSliderList = homeSliderList;
    }

    @Override
    public int getCount() {
        return homeSliderList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        TextView txt_price = (TextView) itemView.findViewById(R.id.txt_price);
        TextView txt_desc = (TextView) itemView.findViewById(R.id.txt_desc);
        LinearLayout ll_container = (LinearLayout) itemView.findViewById(R.id.ll_container);

        Home_slider_list home_slider_list = homeSliderList.get(position);

//        imageView.setImageResource(home_slider_list.img_url);
        txt_price.setText(home_slider_list.title);
        txt_desc.setText(home_slider_list.desc);
//        ll_container.setBackgroundColor(colors.getColor(position, 0));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}