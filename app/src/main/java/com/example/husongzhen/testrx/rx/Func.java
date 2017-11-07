package com.example.husongzhen.testrx.rx;

/**
 * Created by husongzhen on 17/11/7.
 */

public interface Func<T, M> {
    M flowMap(T t);
}
