package com.bhaavtaal.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bhaavtaal.R;
import com.bhaavtaal.models.Categories;
import com.bhaavtaal.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AddItemFragment extends Fragment {


    Activity activity;
    Button btn_submit;
    private List<String> catList = new ArrayList<>();
    Spinner sp_category;

    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance() {
        AddItemFragment fragment = new AddItemFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        View view = inflater.inflate(R.layout.activity_admin_add_item, container, false);

        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        sp_category = (Spinner) view.findViewById(R.id.sp_category);

        getCategories();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void getCategories() {
        try {
            JSONObject cat_list_jobj = new JSONObject(Util.cat_array);
            JSONArray jsonCatArray = cat_list_jobj.getJSONArray("cat_array");

            for (int i = 0; i < jsonCatArray.length(); i++) {
//                Movie movie = new Movie(jsonArray.get", "60 Rs", "2015");
                Categories categories = new Categories();

                categories.cat_id = jsonCatArray.getJSONObject(i).getString("cat_id");
                categories.cat_name = jsonCatArray.getJSONObject(i).getString("cat_name");

                catList.add(jsonCatArray.getJSONObject(i).getString("cat_name"));
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, catList);

            sp_category.setAdapter(dataAdapter);

        } catch (Exception e) {
            Log.d("", "Exception: " + e);
        }
    }

}
