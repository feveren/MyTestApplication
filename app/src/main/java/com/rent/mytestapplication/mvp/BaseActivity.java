package com.rent.mytestapplication.mvp;

import com.rent.mytestapplication.common.view.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * Created by RenTao on 17/1/18.
 */

public class BaseActivity extends RxFragmentActivity implements BaseView {
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event) {
        return null;
    }
}
