package com.example.amanda.qanetzme.presentation.register;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.amanda.qanetzme.R;
import com.example.amanda.qanetzme.data.model.User;
import com.example.amanda.qanetzme.data.sqlite.SqliteController;

/**
 * Created by amanda on 05/02/18.
 */

@SuppressWarnings("WeakerAccess")
public class RegisterPresenter implements RegisterContract.Presenter{

    @NonNull
    RegisterContract.View mView;
    String fname, lname, addess, phone, email, pass;
    Context context;
    SqliteController sqliteController;

    public RegisterPresenter(@NonNull RegisterContract.View view, Context mContext){
        mView = view;
        context = mContext;
        mView.setPresenter(this);
        sqliteController = new SqliteController(context);
    }

    @Override
    public boolean validateRegisterFields(EditText[] fields) {

        for (EditText field: fields){
            if (field.getText().toString().isEmpty()){
                field.setError("Field Required!");
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

        if (savingData(fname, lname, addess, phone, email, pass)){
            mView.showSuccessfulRegister(context.getString(R.string.register_successfully));
        }else{
            mView.showErrorRegister(context.getString(R.string.register_failed));
        }

    }

    private boolean savingData(String fname, String lname, String address,
                               String phone, String email, String pass){
        User user = new User(fname, lname, address, phone, email, pass);
        return sqliteController.saveUserData(user);
    }

    @Override
    public void start() {}
}
