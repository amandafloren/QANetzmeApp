package com.example.amanda.qanetzme.presentation.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amanda.qanetzme.NavigationUtils;
import com.example.amanda.qanetzme.R;
import com.example.amanda.qanetzme.bases.BaseActivity;
import com.example.amanda.qanetzme.presentation.custom.UnderLineText;
import com.example.amanda.qanetzme.presentation.profile.ProfileActivity;
import com.example.amanda.qanetzme.presentation.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;





public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_email_address) EditText et_email;
    @BindView(R.id.et_password) EditText et_pass;
    @BindView(R.id.btn_register) TextView btn_register;
    LoginContract.Presenter mPresenter;
    LoginPresenter loginPresenter;

    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setView() {
        super.setView();
        new UnderLineText(btn_register);
    }

    @OnClick({R.id.btn_register, R.id.btn_login, R.id.btn_Close})
    public void buttonClicked(View v){
        switch (v.getId()){
            case R.id.btn_login:
                loginPresenter.validateLoginFields(new EditText[]{et_email, et_pass});
                break;
            case R.id.btn_register:
                NavigationUtils.startActivity(this,
                        RegisterActivity.class,
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            case R.id.btn_Close:
                finish();
                System.exit(0);
                break;
        }
    }

    @Override
    public void showSuccessfulMessage(String message) {
        showMessageToast(message);
    }

    @Override
    public void showFailedMessage(String message) {
        showMessageToast(message);
    }

    @Override
    public void showMessageToast(String message) {
        super.showMessageToast(message);
    }

    @Override
    public void navigateTo(String email) {
        NavigationUtils.startActivity(this,
                ProfileActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP,
                email);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter = new LoginPresenter(this, this);
    }
}
