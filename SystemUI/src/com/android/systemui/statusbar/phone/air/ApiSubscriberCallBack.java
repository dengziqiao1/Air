package com.android.systemui.statusbar.phone.air;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import com.android.systemui.statusbar.phone.air.LogUtils;

/**
 * Rxjava2 回调
 */
public abstract class ApiSubscriberCallBack<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        try {
            onPiteSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
           LogUtils.e("fce", "Rxjava Exception:  " + e.toString());
        }
    }

    @Override
    public void onError(Throwable t) {
        onPiteFailure(t);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onPiteSuccess(T t);

    public abstract void onPiteFailure(Throwable e);
}
