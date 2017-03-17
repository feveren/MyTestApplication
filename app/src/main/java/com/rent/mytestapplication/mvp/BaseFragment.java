package com.rent.mytestapplication.mvp;

import com.rent.mytestapplication.common.view.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 *
 * Created by aa on 2017/3/15.
 */
public class BaseFragment extends RxFragment implements BaseView {

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent event) {
        return null;
    }
}
