package com.example.husongzhen.testrx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.name).setOnClickListener(this);
        findViewById(R.id.view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.name:
                startActivity(new Intent(this, OtherActivity.class));
                break;


            case R.id.view:

                startActivity(new Intent(this, SwipActivity.class));

                break;
        }


    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
