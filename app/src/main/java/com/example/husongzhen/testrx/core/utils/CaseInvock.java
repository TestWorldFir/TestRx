package com.example.husongzhen.testrx.core.utils;

/**
 * Created by husongzhen on 17/12/15.
 */

public interface CaseInvock<P, R> {
    R call(P p);
}
