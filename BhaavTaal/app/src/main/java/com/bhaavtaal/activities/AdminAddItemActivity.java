package com.bhaavtaal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bhaavtaal.R;
import com.bhaavtaal.authentication.VerifyOTPActivity;
import com.bhaavtaal.models.Categories;
import com.bhaavtaal.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER 8 on 29-Aug-16.
 */
public class AdminAddItemActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_submit;
    private List<String> catList = new ArrayList<>();
    Spinner sp_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        sp_category = (Spinner) findViewById(R.id.sp_category);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminAddItemActivity.this, VerifyOTPActivity.class));
            }
        });

        getCategories();
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

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, catList);

            sp_category.setAdapter(dataAdapter);

        } catch (Exception e) {
            Log.d("", "Exception: " + e);
        }
    }
}
