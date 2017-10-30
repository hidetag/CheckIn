package cn.shield.checkin.net;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Observable.from(someSource)
 * .map(new Func1<Data, Data>() {
 *
 * @Override public Data call(Data data) {
 * return manipulate(data);
 * }
 * })
 * .compose(this.<YourType>applySchedulers())
 * .subscribe(new Action1<Data>() {
 * @Override public void call(Data data) {
 * doSomething(data);
 * }
 * });
 * <p>
 * <p>
 * 线程切换器
 * 在compose()之前为工作线程，在compose之后为主线程
 */
public class SchedulersFlowableCompat {
    private static final FlowableTransformer computationTransformer =
            upstream -> upstream.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
    private static final FlowableTransformer ioTransformer =
            upstream -> upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    private static final FlowableTransformer newTransformer =
            upstream -> upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
    private static final FlowableTransformer trampolineTransformer = upstream ->
            upstream.subscribeOn(Schedulers.trampoline())
                    .observeOn(AndroidSchedulers.mainThread());
    private static final FlowableTransformer executorTransformer = upstream ->
            upstream.subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
                    .observeOn(AndroidSchedulers.mainThread());

    /**
     * Don't break the chain: use RxJava's compose() operator
     */
    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> applyComputationSchedulers() {
        return computationTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> applyIoSchedulers() {
        return ioTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> applyNewSchedulers() {
        return (FlowableTransformer<T, T>) newTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> applyTrampolineSchedulers() {
        return trampolineTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> applyExecutorSchedulers() {
        return executorTransformer;
    }
}
