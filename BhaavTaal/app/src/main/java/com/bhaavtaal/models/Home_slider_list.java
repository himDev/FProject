package com.bhaavtaal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER 8 on 25-Aug-16.
 */
public class Home_slider_list implements Parcelable {
    public String title;
    public String desc;
    public String img_url;
    public String posted_by;
    public String shop_keeper_id;
    public String post_id;
    public String posted_datetime;
    public String category_id;
    public String distance_from_you;

    public Home_slider_list(Parcel in) {
        title = in.readString();
        desc = in.readString();
        img_url = in.readString();
        posted_by = in.readString();
        shop_keeper_id = in.readString();
        post_id = in.readString();
        posted_datetime = in.readString();
        category_id = in.readString();
        distance_from_you = in.readString();
    }

    public Home_slider_list() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(img_url);
        dest.writeString(posted_by);
        dest.writeString(shop_keeper_id);
        dest.writeString(post_id);
        dest.writeString(posted_datetime);
        dest.writeString(category_id);
        dest.writeString(distance_from_you);
    }

    @SuppressWarnings("unused")
    public static final Creator<Home_slider_list> CREATOR = new Creator<Home_slider_list>() {
        @Override
        public Home_slider_list createFromParcel(Parcel in) {
            return new Home_slider_list(in);
        }

        @Override
        public Home_slider_list[] newArray(int size) {
            return new Home_slider_list[size];
        }
    };
}