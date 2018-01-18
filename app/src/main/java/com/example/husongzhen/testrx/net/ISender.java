package com.example.husongzhen.testrx.net;

/**
 * Created by husongzhen on 2018/1/18.
 */

public abstract class ISender<R> {


    public <P> ISender<P> map(Func<R, P> func) {
        return new ISender<P>() {
            @Override
            public void sender(IResult<P> result) {
                ISender.this.sender(new IResult<R>() {
                    @Override
                    public void onSucc(R r) {
                        result.onSucc(func.fun(r));
                    }

                    @Override
                    public void onError(Exception r) {

                    }
                });


            }
        };
    }


    public abstract void sender(IResult<R> result);

}
