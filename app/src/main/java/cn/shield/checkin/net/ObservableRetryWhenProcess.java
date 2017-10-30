package cn.shield.checkin.net;

/**
 * * * * * * * * * * * * * * * * * * * * * * *
 * Created by lixin
 * Date: 16/2/18
 * * * * * * * * * * * * * * * * * * * * * * *
 **/

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/**
 * 封装了RxJava的RetryWhen()
 */
public class ObservableRetryWhenProcess implements Function<Observable<Throwable>, ObservableSource<?>> {

//    public static final String ERROR_404 = "retrofit2.adapter.rxjava.HttpException: HTTP 404 Not Found";

    private long mInterval;
    private int mCount;
    public Throwable mThrowable;

    public ObservableRetryWhenProcess(long interval) {
        this(interval, 3);
    }


    public ObservableRetryWhenProcess(long interval, int count) {
        this(interval, count, true);
    }


    public ObservableRetryWhenProcess(long interval, int count, boolean isSecond) {
        mInterval = isSecond ? interval * 1000 : interval;
        mCount = count + 1;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> errors) throws Exception {
        return errors.flatMap(throwable1 -> {
            mThrowable = throwable1;
            return errors;
        }).zipWith(Observable.range(1, mCount), (throwable, i) -> i)
                .flatMap(retryCount -> {
                    if (retryCount == mCount && mThrowable != null) {
                        return Observable.error(mThrowable);
                    }
                    return Observable.timer(mInterval, TimeUnit.MILLISECONDS);
                });
    }
}
