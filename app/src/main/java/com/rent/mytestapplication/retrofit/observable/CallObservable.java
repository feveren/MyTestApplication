package com.rent.mytestapplication.retrofit.observable;

import com.rent.mytestapplication.retrofit.transformer.ThreadTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;

/**
 *
 * Created by RenTao on 17/1/15.
 */
public abstract class CallObservable<T> extends Observable<T> {

    public void subscribe(Object component, Observer<T> observer) {
        composeCommon(component).subscribe(observer);
    }

    public Observable<T> composeCommon(Object component) {
        return composeBind(component).compose(new ThreadTransformer<T>());
    }

    public Observable<T> composeThread() {
        return compose(new ThreadTransformer<T>());
    }

    @SuppressWarnings("UnusedAssignment")
    public Observable<T> composeBind(Object component) {
        // 方法内部会判断component是不是RxFragmentActivity，如果是则进行绑定，否则返回this
        Observable<T> obz = composeBind(component, ActivityEvent.DESTROY);
        // 再判断component是不是RxFragment或RxDialogFragment，如果是则进行绑定，否则返回this
        obz = composeBind(component, FragmentEvent.DESTROY);
        return obz;
    }

    public Observable<T> composeBind(Object component, ActivityEvent event) {
        ObservableTransformer<T, T> transformer = null;
        if (component instanceof RxFragmentActivity) {
            transformer = ((RxFragmentActivity) component).bindUntilEvent(event);
        }
        if (transformer != null) {
            return compose(transformer);
        }
        return this;
    }

    public Observable<T> composeBind(Object component, FragmentEvent event) {
        ObservableTransformer<T, T> transformer = null;
        if (component instanceof RxFragment) {
            transformer = ((RxFragment) component).bindUntilEvent(event);
        }
        if (component instanceof RxDialogFragment) {
            transformer = ((RxDialogFragment) component).bindUntilEvent(event);
        }
        if (transformer != null) {
            return compose(transformer);
        }
        return this;
    }
}