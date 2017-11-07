package com.example.husongzhen.testrx.rx;

import android.app.Activity;
import android.view.View;

import java.util.List;

/**
 * Created by husongzhen on 17/11/7.
 */

public class TaskClient<T> {

    private ViewObservable base;

    public final TaskClient<T> createTask(ViewObservable<T> observable) {
        this.base = observable;
        return this;
    }


    public void start(SubSubject<T> subSubject) {
        this.base.subscribe(subSubject);
    }


    public TaskClient<View> click() {
        return new TaskClient<View>().createTask(new ViewObservable<View>() {
            @Override
            public void subscribe(final SubSubject<View> subSubject) {
                base.subscribe(new SubSubject<View>() {
                    @Override
                    public void next(View o) {
                        o.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                subSubject.next(v);
                            }
                        });
                    }
                });
            }
        });
    }

    public TaskClient<Integer> getWidth() {
        return new TaskClient<Integer>().createTask(new ViewObservable<Integer>() {
            @Override
            public void subscribe(final SubSubject<Integer> subSubject) {
                base.subscribe(new SubSubject<View>() {
                    @Override
                    public void next(View o) {
                        subSubject.next(o.getWidth());
                    }
                });
            }
        });
    }

    public TaskClient id(final Activity activity, final int id) {
        return new TaskClient().createTask(new ViewObservable() {
            @Override
            public void subscribe(SubSubject subSubject) {
                subSubject.next(activity.findViewById(id));
            }
        });
    }

    public <M> TaskClient<List> flowMap(final Func func) {
        return new TaskClient().createTask(new ViewObservable<M>() {
            @Override
            public void subscribe(final SubSubject<M> subSubject) {
                base.subscribe(new SubSubject<List>() {
                    @Override
                    public void next(List list) {
                        for (Object object : list) {
                            subSubject.next((M) func.flowMap(object));
                        }
                    }
                });
            }
        });


    }
}
