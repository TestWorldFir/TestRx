package com.example.husongzhen.testrx;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.reactivex.annotations.Nullable;

public class SwipActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip_view);




        findViewById(R.id.child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipActivity.this, "child", Toast.LENGTH_SHORT).show();
            }
        });








    }
}
