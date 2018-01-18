package com.example.husongzhen.testrx.net;

/**
 * Created by husongzhen on 2018/1/18.
 */

public interface IResult<R> {


    void onSucc(R r);


    void onError(Exception r);


}
