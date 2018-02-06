package com.example.amanda.qanetzme.presentation.login;

import android.widget.EditText;

import com.example.amanda.qanetzme.bases.BasePresenter;
import com.example.amanda.qanetzme.bases.BaseView;

/**
 * Created by amanda on 31/01/18.
 */
@SuppressWarnings("WeakerAccess")
public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showSuccessfulMessage(String message);
        void showFailedMessage(String message);
        void navigateTo(String email);
    }
    interface Presenter extends BasePresenter {
        boolean validateLoginFields(EditText[] fields);
    }
}
