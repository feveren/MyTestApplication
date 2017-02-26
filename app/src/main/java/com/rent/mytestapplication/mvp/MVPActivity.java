package com.rent.mytestapplication.mvp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.mvp.presenter.MVPPresenter;
import com.rent.mytestapplication.mvp.presenter.impl.MVPPresenterImpl;
import com.rent.mytestapplication.mvp.view.MVPView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPActivity extends BaseActivity implements MVPView {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    private MVPPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        mvpPresenter = new MVPPresenterImpl();
        mvpPresenter.bindView(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        mvpPresenter.login(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        password.setText("");
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
}
