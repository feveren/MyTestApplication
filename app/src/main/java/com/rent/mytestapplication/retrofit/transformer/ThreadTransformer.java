package com.rent.mytestapplication.retrofit.transformer;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * subscribeOn io()<br/>
 * unsubscribeOn io()<br/>
 * observeOn scheduler default mainThread()<br/>
 * Created by RenTao on 2017/3/15.
 */
public class ThreadTransformer<T> implements ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer {
    private Scheduler mScheduler;

    public ThreadTransformer() {
        this(AndroidSchedulers.mainThread());
    }

    public ThreadTransformer(Scheduler scheduler) {
        this.mScheduler = scheduler;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        upstream = upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (mScheduler != null) {
            upstream = upstream.observeOn(mScheduler);
        }
        return upstream;
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return null;
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return null;
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return null;
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return null;
    }
}
