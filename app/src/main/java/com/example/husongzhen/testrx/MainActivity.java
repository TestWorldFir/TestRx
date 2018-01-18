package com.example.husongzhen.testrx;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.husongzhen.testrx.net.Func;
import com.example.husongzhen.testrx.net.IResult;
import com.example.husongzhen.testrx.net.NetRequest;

public class MainActivity extends Activity {

    private static final String TAG = "main_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.name)
                .setOnClickListener(v -> new NetRequest().post("name ")
                .map(new Func<String, Integer>() {
                    @Override
                    protected Integer fun(String r) {
                        return r.hashCode();
                    }
                })
                .sender(new IResult<Integer>() {
                    @Override
                    public void onSucc(Integer integer) {
                        toast(integer + "");
                    }

                    @Override
                    public void onError(Exception r) {

                    }
                }));
    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
