package com.rent.mytestapplication.common.view;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 *
 * Created by RenTao on 17/1/17.
 */
public interface BaseView {

    <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event);

    <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event);
}
