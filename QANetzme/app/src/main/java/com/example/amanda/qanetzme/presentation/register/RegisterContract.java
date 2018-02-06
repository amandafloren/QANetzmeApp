package com.example.amanda.qanetzme.presentation.register;

import android.widget.EditText;

import com.example.amanda.qanetzme.bases.BasePresenter;
import com.example.amanda.qanetzme.bases.BaseView;

/**
 * Created by amanda on 05/02/18.
 */
@SuppressWarnings("WeakerAccess")
public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showSuccessfulRegister(String message);
        void showErrorRegister(String message);
        void navigateTo();
    }

    interface Presenter extends BasePresenter {
        boolean validateRegisterFields(EditText[] fields);
    }

}
