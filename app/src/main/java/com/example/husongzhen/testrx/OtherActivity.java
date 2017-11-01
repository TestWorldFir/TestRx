package com.example.husongzhen.testrx;

import android.os.Bundle;

import com.allen.coder.swipe.SwipeBackActivity;
import com.allen.coder.swipe.SwipeBackLayout;

import io.reactivex.annotations.Nullable;

public class OtherActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_view);
        setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);






    }
}
