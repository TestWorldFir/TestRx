package com.example.husongzhen.testrx.net;

/**
 * Created by husongzhen on 2018/1/18.
 */

public class NetRequest {


    public ISender<String> post(String p) {
        return new ISender<String>() {
            @Override
            public void sender(IResult<String> result) {
                result.onSucc(p);
            }
        };


    }
}
