package com.example.husongzhen.testrx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.husongzhen.testrx.rx.Func;
import com.example.husongzhen.testrx.rx.SubSubject;
import com.example.husongzhen.testrx.rx.TaskClient;
import com.example.husongzhen.testrx.rx.ViewObservable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "main_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.name).setOnClickListener(this);
        findViewById(R.id.view).setOnClickListener(this);
        findViewById(R.id.bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TaskClient()
                        .createTask(new ViewObservable<List>() {
                            @Override
                            public void subscribe(SubSubject<List> subSubject) {
                                List list = new ArrayList();
                                list.add("oe1n");
                                list.add("oen1");
                                list.add("oen2");
                                list.add("oen3");
                                subSubject.next(list);
                            }
                        })
                        .flowMap(new Func() {
                            @Override
                            public Object flowMap(Object o) {
                                return o;
                            }
                        })
                        .start(new SubSubject() {
                            @Override
                            public void next(Object o) {
                                Log.e(TAG, "next: " + o);
                            }

                        });
            }
        });
    }

    @Override
    public void onClick(View v) {

//
//        Observable
//                .create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//
//                    }
//                })
//                .flatMap(new Function<Object, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(final Object o) throws Exception {
//                        return new ObservableSource<Object>() {
//                            @Override
//                            public void subscribe(Observer<? super Object> observer) {
//                                observer.onNext(o);
//                            }
//                        };
//                    }
//                });


//                .id(this, R.id.name)
//                .click()
//                .getWidth()
//                .start(new SubSubject() {
//                    @Override
//                    public void next(Object o) {

//                    }
//                });

//
//        switch (v.getId()) {
//            case R.id.name:
//                startActivity(new Intent(this, OtherActivity.class));
//                break;
//
//
//            case R.id.view:
//
//                startActivity(new Intent(this, SwipActivity.class));
//
//                break;
//        }


    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
