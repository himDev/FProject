package com.bhaavtaal.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bhaavtaal.R;
import com.bhaavtaal.activities.AdminAddItemActivity;

/**
 * Created by USER 8 on 29-Aug-16.
 */
public class VerifyOTPActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        Button btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyOTPActivity.this, AdminAddItemActivity.class));
            }
        });
    }
}
