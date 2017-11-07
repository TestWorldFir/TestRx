package com.example.husongzhen.testrx.rx;

/**
 * Created by husongzhen on 17/11/7.
 */

public interface ViewObservable<T> {


    void subscribe(SubSubject<T> subSubject);


}
