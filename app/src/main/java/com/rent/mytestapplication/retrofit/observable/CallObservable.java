package com.rent.mytestapplication.retrofit.observable;

import com.rent.mytestapplication.common.view.BaseView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by RenTao on 17/1/15.
 */
public abstract class CallObservable<T> extends Observable<T> {

    // RxFragmentActivity ===========================

    public void subscribe(RxFragmentActivity activity, Observer<T> observer) {
        subscribe(activity, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxFragmentActivity activity, Scheduler scheduler, Observer<T> observer) {
        subscribe(activity, ActivityEvent.DESTROY, scheduler, observer);
    }

    public void subscribe(RxFragmentActivity activity, ActivityEvent bindUntil, Observer<T> observer) {
        subscribe(activity, bindUntil, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxFragmentActivity activity, ActivityEvent bindUntil, Scheduler scheduler, Observer<T> observer) {
        Observable<T> obz = composeThread(scheduler);
        if (activity != null && bindUntil != null) {
            obz.compose(activity.<T>bindUntilEvent(bindUntil));
        }
        obz.subscribe(observer);
    }

    // RxFragment ===========================

    public void subscribe(RxFragment fragment, Observer<T> observer) {
        subscribe(fragment, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxFragment fragment, Scheduler scheduler, Observer<T> observer) {
        subscribe(fragment, FragmentEvent.DESTROY, scheduler, observer);
    }

    public void subscribe(RxFragment fragment, FragmentEvent bindUntil, Observer<T> observer) {
        subscribe(fragment, bindUntil, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxFragment fragment, FragmentEvent bindUntil, Scheduler scheduler, Observer<T> observer) {
        Observable<T> obz = composeThread(scheduler);
        if (fragment != null && bindUntil != null) {
            obz.compose(fragment.<T>bindUntilEvent(bindUntil));
        }
        obz.subscribe(observer);
    }

    // RxDialogFragment ===========================

    public void subscribe(RxDialogFragment fragment, Observer<T> observer) {
        subscribe(fragment, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxDialogFragment fragment, Scheduler scheduler, Observer<T> observer) {
        subscribe(fragment, FragmentEvent.DESTROY, scheduler, observer);
    }

    public void subscribe(RxDialogFragment fragment, FragmentEvent bindUntil, Observer<T> observer) {
        subscribe(fragment, bindUntil, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(RxDialogFragment fragment, FragmentEvent bindUntil, Scheduler scheduler, Observer<T> observer) {
        Observable<T> obz = composeThread(scheduler);
        if (fragment != null && bindUntil != null) {
            obz.compose(fragment.<T>bindUntilEvent(bindUntil));
        }
        obz.subscribe(observer);
    }

    // MVP ===========================

    public void subscribe(BaseView view, Observer<T> observer) {
        subscribe(view, AndroidSchedulers.mainThread(), observer);
    }

    public void subscribe(BaseView view, Scheduler scheduler, Observer<T> observer) {
        Observable<T> obz = composeThread(scheduler);
        if (view != null) {
            ObservableTransformer transformer = view.bindUntilEvent(ActivityEvent.DESTROY);
            if (transformer == null) {
                transformer = view.bindUntilEvent(FragmentEvent.DESTROY);
            }
            if (transformer != null) {
                obz.compose(transformer);
            }
        }
        obz.subscribe(observer);
    }

    /**
     * 1.subscribeOn io<br/>
     * 2.unsubscribeOn io<br/>
     * 3.observeOn scheduler
     */
    public Observable<T> composeThread(Scheduler scheduler) {
        Observable<T> obz = subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (scheduler != null) {
            obz.observeOn(scheduler);
        }
        return obz;
    }
}