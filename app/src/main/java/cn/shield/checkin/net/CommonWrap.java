package cn.shield.checkin.net;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 16:29<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class CommonWrap {

    @SuppressWarnings("unchecked")
    private static final FlowableTransformer mFlowableWraper = upstream -> upstream
            .retryWhen(new FlowableRetryWhenProcess(3))
            .compose(SchedulersFlowableCompat.applyExecutorSchedulers());

    @SuppressWarnings("unchecked")
    private static final ObservableTransformer mObservableWraper = upstream -> upstream
            .retryWhen(new ObservableRetryWhenProcess(3))
            .compose(SchedulersObservableCompat.applyExecutorSchedulers());

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> flowableWrap() {
        return mFlowableWraper;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer observableWrap() {
        return mObservableWraper;
    }
}
