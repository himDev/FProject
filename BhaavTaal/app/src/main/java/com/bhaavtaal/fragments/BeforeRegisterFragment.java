package com.bhaavtaal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bhaavtaal.R;
import com.bhaavtaal.authentication.RegisterAsAdmin;
import com.bhaavtaal.authentication.RegisterAsShopKeeperActivity;


public class BeforeRegisterFragment extends Fragment {


    Activity activity;

    // TODO: Rename and change types and number of parameters
    public static BeforeRegisterFragment newInstance() {
        BeforeRegisterFragment fragment = new BeforeRegisterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_before_register, container, false);

        Button btn_admin = (Button) view.findViewById(R.id.btn_register_as_admin);
        Button btn_shopKeeper = (Button) view.findViewById(R.id.btn_register_as_shopkeep);

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterAsAdmin.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });

        btn_shopKeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterAsShopKeeperActivity.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
