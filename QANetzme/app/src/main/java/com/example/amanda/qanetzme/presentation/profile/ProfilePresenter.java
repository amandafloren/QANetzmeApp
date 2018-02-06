package com.example.amanda.qanetzme.presentation.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.amanda.qanetzme.R;
import com.example.amanda.qanetzme.data.model.User;
import com.example.amanda.qanetzme.data.sqlite.SqliteController;

/**
 * Created by amanda on 05/02/18.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    @NonNull
    ProfileContract.View mView;
    Context mContext;
    SqliteController sqliteController;
    String fname, lname, addess, phone, email, pass;

    public ProfilePresenter(@NonNull ProfileContract.View view, Context context){
        mView = view;
        mContext = context;
        mView.setPresenter(this);
        sqliteController = new SqliteController(context);
    }

    @Override
    public void getUserEmail(String email) {
        sqliteController.getUserDetails(email);
        getUserDetail(sqliteController.getUserDetails(email));
    }

    @Override
    public void getUserDetail(User user) {
        mView.loadUserDetail(user);
    }

    @Override
    public void deleteAccountPermanent(String email) {
        sqliteController.deleteAccount(email);
        if (!sqliteController.deleteAccount(email)){
            mView.showSuccess(mContext.getString(R.string.delete_successful));
            mView.navigateToNextPage();
        }else{
            mView.showFailed(mContext.getString(R.string.delete_failed));
        }
    }

    @Override
    public boolean validateUpdateFields(EditText[] fields) {
        for (EditText fieldsCounter: fields) {
            if (fieldsCounter.getText().toString().isEmpty()){
                fieldsCounter.setError("Field Required!");
                return false;
            }
        }
        validated(fields);
        return true;
    }

    private void validated(EditText[] fields){

        fname = fields[0].getText().toString();
        lname = fields[1].getText().toString();
        addess = fields[2].getText().toString();
        phone = fields[3].getText().toString();
        email = fields[4].getText().toString();
        pass = fields[5].getText().toString();

        if (updateUserData(fname, lname, addess, phone, email, pass) == 1){
            mView.showSuccess(mContext.getString(R.string.update_successfully));
            mView.refreshPage(email);
        }else{
            mView.showFailed(mContext.getString(R.string.update_failed));
        }

    }

    @Override
    public void logOut(String email) {
        if (email != null){
            mView.logOut();
        }
    }

    private int updateUserData(String fname, String lname, String address, String phone, String email, String pass){
        User user = new User(fname, lname, address, phone, email, pass);
        return sqliteController.updateUserData(user, email);
    }

    @Override
    public void start() {}
}
