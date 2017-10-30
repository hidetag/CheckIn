package cn.shield.checkin.net;

/**
 * * * * * * * * * * * * * * * * * * * * * * *
 * Created by lixin
 * Date: 16/2/18
 * * * * * * * * * * * * * * * * * * * * * * *
 **/

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * 封装了RxJava的RetryWhen()
 */
public class FlowableRetryWhenProcess implements Function<Flowable<Throwable>, Publisher<?>> {

//    public static final String ERROR_404 = "retrofit2.adapter.rxjava.HttpException: HTTP 404 Not Found";

    private long mInterval;
    private int mCount;
    public Throwable mThrowable;

    public FlowableRetryWhenProcess(long interval) {
        this(interval, 3);
    }


    public FlowableRetryWhenProcess(long interval, int count) {
        this(interval, count, true);
    }


    public FlowableRetryWhenProcess(long interval, int count, boolean isSecond) {
        mInterval = isSecond ? interval * 1000 : interval;
        mCount = count + 1;
    }

    @Override
    public Publisher<?> apply(Flowable<Throwable> errors) throws Exception {
        return errors.flatMap(throwable1 -> {
            mThrowable = throwable1;
            return errors;
        }).zipWith(Flowable.range(1, mCount), (throwable, i) -> i)
                .flatMap(retryCount -> {
                    if (retryCount == mCount && mThrowable != null) {
                        return Flowable.error(mThrowable);
                    }
                    return Flowable.timer(mInterval, TimeUnit.MILLISECONDS);
                });
    }



}
