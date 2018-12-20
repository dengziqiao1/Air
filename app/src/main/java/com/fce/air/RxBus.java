package com.fce.air;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * RXBUS 事件
 */
public class RxBus {
    private FlowableProcessor<Object> flowable;

    private static class Holder {
        private static RxBus instance = new RxBus();
    }

    public RxBus() {
        flowable = PublishProcessor.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    /**
     * @param tClass
     * @param <T>
     * @return 根据 特定类型返回数据类型
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return setThread(flowable.ofType(tClass));
    }
    /**
     * @param tClass
     * @param <T>
     * @return 根据 特定类型返回数据类型
     */
    public <T> Flowable<T> toSleepFlowable(Class<T> tClass) {
        return setThread(flowable.ofType(tClass).delay(300,TimeUnit.MILLISECONDS));
    }
    /**
     * @return 任意类型数据
     */
    public Flowable<Object> toFlowable() {
        return setThread(flowable);
    }

    /**
     * 发送数据
     *
     * @param object
     */
    public void toSend(Object object) {
        flowable.onNext(object);
    }

    /**
     * @param observable
     * @param <T>
     * @return 设置线程
     */
    private <T> Flowable<T> setThread(Flowable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
