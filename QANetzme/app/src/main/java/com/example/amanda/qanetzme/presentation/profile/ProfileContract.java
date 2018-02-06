package com.example.amanda.qanetzme.presentation.profile;

import android.widget.EditText;

import com.example.amanda.qanetzme.bases.BasePresenter;
import com.example.amanda.qanetzme.bases.BaseView;
import com.example.amanda.qanetzme.data.model.User;

/**
 * Created by amanda on 05/02/18.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {
        void loadUserDetail(User user);
        void showSuccess(String message);
        void showFailed(String message);
        void navigateToNextPage();
        void refreshPage(String email);
        void logOut();
    }

    interface Presenter extends BasePresenter {
        void getUserEmail(String email);
        void logOut(String email);
        void getUserDetail(User user);
        void deleteAccountPermanent(String email);
        boolean validateUpdateFields(EditText[] fields);
    }

}
