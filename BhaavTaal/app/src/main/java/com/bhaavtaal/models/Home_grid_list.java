package com.bhaavtaal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER 8 on 25-Aug-16.
 */
public class Home_grid_list implements Parcelable {
    public String item_name;
    public String item_category;
    public String item_image;
    public String title;
    public String desc;
    public String price;
    public String lat;
    public String lng;
    public String location_address;
    public String distance_from_current;

    public Home_grid_list(Parcel in) {
        item_name = in.readString();
        item_category = in.readString();
        item_image = in.readString();
        title = in.readString();
        desc = in.readString();
        price = in.readString();
        lat = in.readString();
        lng = in.readString();
        location_address = in.readString();
        distance_from_current = in.readString();
    }

    public Home_grid_list() {
        
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_name);
        dest.writeString(item_category);
        dest.writeString(item_image);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(price);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(location_address);
        dest.writeString(distance_from_current);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Home_grid_list> CREATOR = new Parcelable.Creator<Home_grid_list>() {
        @Override
        public Home_grid_list createFromParcel(Parcel in) {
            return new Home_grid_list(in);
        }

        @Override
        public Home_grid_list[] newArray(int size) {
            return new Home_grid_list[size];
        }
    };
}