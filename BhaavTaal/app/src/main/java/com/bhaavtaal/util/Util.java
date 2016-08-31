package com.bhaavtaal.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by USER 8 on 24-Aug-16.
 */
public class Util {

    public static String home_grid_json = "{\n" +
            "\n" +
            "\"slider_items\":\n" +
            "[\n" +
            "{\n" +
            "\t\"title\":\"slider 1\",\n" +
            "\t\"desc\":\"this is slider 1\",\n" +
            "\t\"img_url\":\"https://cdn3.iconfinder.com/data/icons/food-nutrition/92/tomato-128.png\",\n" +
            "\t\"posted_by\":\"karnavati\",\n" +
            "\t\"shop_keeper_id\":\"2\",\n" +
            "\t\"post_id\":\"6\",\n" +
            "\t\"posted_datetime\":\"3 Aug 2016\",\n" +
            "\t\"category_id\":\"1\",\n" +
            "\t\"distance_from_you\":\"2.3 kms\"\n" +
            "\t\n" +
            "\t\n" +
            "},\n" +
            "{\n" +
            "\t\"title\":\"slider 2\",\n" +
            "\t\"desc\":\"this is slider 1\",\n" +
            "\t\"img_url\":\"https://cdn3.iconfinder.com/data/icons/food-nutrition/92/tomato-128.png\",\n" +
            "\t\"posted_by\":\"karnavati\",\n" +
            "\t\"shop_keeper_id\":\"2\",\n" +
            "\t\"post_id\":\"6\",\n" +
            "\t\"posted_datetime\":\"3 Aug 2016\",\n" +
            "\t\"category_id\":\"1\",\n" +
            "\t\"distance_from_you\":\"2.3 kms\"\n" +
            "},\n" +
            "{\n" +
            "\t\"title\":\"slider 3\",\n" +
            "\t\"desc\":\"this is slider 1\",\n" +
            "\t\"img_url\":\"https://cdn3.iconfinder.com/data/icons/food-nutrition/92/tomato-128.png\",\n" +
            "\t\"posted_by\":\"karnavati\",\n" +
            "\t\"shop_keeper_id\":\"2\",\n" +
            "\t\"post_id\":\"6\",\n" +
            "\t\"posted_datetime\":\"3 Aug 2016\",\n" +
            "\t\"category_id\":\"1\",\n" +
            "\t\"distance_from_you\":\"2.3 kms\"\n" +
            "},\n" +
            "{\n" +
            "\t\"title\":\"slider 4\",\n" +
            "\t\"desc\":\"this is slider 1\",\n" +
            "\t\"img_url\":\"https://cdn3.iconfinder.com/data/icons/food-nutrition/92/tomato-128.png\",\n" +
            "\t\"posted_by\":\"karnavati\",\n" +
            "\t\"shop_keeper_id\":\"2\",\n" +
            "\t\"post_id\":\"6\",\n" +
            "\t\"posted_datetime\":\"3 Aug 2016\",\n" +
            "\t\"category_id\":\"1\",\n" +
            "\t\"distance_from_you\":\"2.3 kms\"\n" +
            "}\n" +
            "],\n" +
            "\n" +
            "\"home_grid_list\":\n" +
            "[{\n" +
            "\"item_name\":\"Tomato\",\n" +
            "\"item_category\":\"vegitables\",\n" +
            "\"item_image\":\"https://cdn3.iconfinder.com/data/icons/food-nutrition/92/tomato-128.png\",\n" +
            "\"title\":\"Tomato\",\n" +
            "\"desc\":\"\",\n" +
            "\"price\":\"65 Rs Per Kg\",\n" +
            "\"lat\":\"\",\n" +
            "\"lng\":\"\",\n" +
            "\"location_address\":\"satelite\",\n" +
            "\"distance_from_current\":\"2.5 km\"\n" +
            "},\n" +
            "{\n" +
            "\"item_name\":\"Chilli\",\n" +
            "\"item_category\":\"vegitables\",\n" +
            "\"item_image\":\"https://cdn4.iconfinder.com/data/icons/gastronomy-pack-1/50/chili-128.png\",\n" +
            "\"title\":\"Chilli\",\n" +
            "\"desc\":\"\",\n" +
            "\"price\":\"65 Rs Per Kg\",\n" +
            "\"lat\":\"\",\n" +
            "\"lng\":\"\",\n" +
            "\"location_address\":\"satelite\",\n" +
            "\"distance_from_current\":\"2.5 km\"\n" +
            "},\n" +
            "{\n" +
            "\"item_name\":\"Potato\",\n" +
            "\"item_category\":\"vegitables\",\n" +
            "\"item_image\":\"https://cdn0.iconfinder.com/data/icons/smashicons-gastronomy-greyscale/57/209_-_Potatoes_gastronomy_cook_kitchen_-128.png\",\n" +
            "\"title\":\"Potatp\",\n" +
            "\"desc\":\"\",\n" +
            "\"price\":\"65 Rs Per Kg\",\n" +
            "\"lat\":\"\",\n" +
            "\"lng\":\"\",\n" +
            "\"location_address\":\"satelite\",\n" +
            "\"distance_from_current\":\"2.5 km\"\n" +
            "}\n" +
            "\n" +
            "]}";

    public static String cat_array = "{\n" +
            "\"cat_array\":[\n" +
            "{\"cat_id\":\"1\",\n" +
            "\"cat_name\":\"Vegetables\"\n" +
            "},\n" +
            "{\"cat_id\":\"2\",\n" +
            "\"cat_name\":\"Fruits\"\n" +
            "},\n" +
            "{\"cat_id\":\"3\",\n" +
            "\"cat_name\":\"Stationary\"\n" +
            "},\n" +
            "{\"cat_id\":\"4\",\n" +
            "\"cat_name\":\"Provision\"\n" +
            "}\n" +
            "]\n" +
            "}";

    public static String getAddressFromLtLong(Context context, double lat, double lng) {

        String add = "";
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            add = address + ", " + city;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return add;
    }
}
