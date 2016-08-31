package com.bhaavtaal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER 8 on 29-Aug-16.
 */
public class Categories implements Parcelable {
    public String cat_id="";
    public String cat_name="";

    public Categories(){

    }
    protected Categories(Parcel in) {
        cat_id = in.readString();
        cat_name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cat_id);
        dest.writeString(cat_name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Categories> CREATOR = new Parcelable.Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
