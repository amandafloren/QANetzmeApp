package com.example.amanda.qanetzme.presentation.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.amanda.qanetzme.R;
import com.example.amanda.qanetzme.data.sqlite.SqliteController;

/**
 * Created by amanda on 31/01/18.
 */
// Memberitahukan compiler bahwa class ini merupakan class subclass dari superclassnya yaitu LoginContract
@SuppressWarnings("WeakerAccess")
public class LoginPresenter implements LoginContract.Presenter {
    @NonNull LoginContract.View mView;
    Context mContext;
    String email, pass;
    SqliteController sqliteController;

    public LoginPresenter(@NonNull LoginContract.View view, Context context){
        mView = view;
        mView.setPresenter(this);
        mContext = context;
        sqliteController = new SqliteController(context);
    }

    /**
     * Metode untuk mengecek apakah field dalam keadaan null atau tidak dan me return nilai boolean.
     * @param fields parameter ini menampung objek EditText
     * @return true/false
     **/
    @Override
    public boolean validateLoginFields(EditText[] fields) {
        for (EditText field: fields){
            if (field.getText().toString().isEmpty()){
                field.setError("Provide the required field!");
                return false;
            }
        }
        validated(fields);
        return true;
    }

    private void validated(EditText[] fields){
        email = fields[0].getText().toString();
        pass = fields[1].getText().toString();
        checkCredentials(email, pass);
    }

    /**
     * Metode untuk mengecek User ada atau tidak
     * @param email parameter yang menampung value dari email
     * @param pass parameter yang menampung value dari pass
     **/
    private void checkCredentials(String email, String pass){
        if (sqliteController.checkUserCredentials(email, pass)){
            mView.showSuccessfulMessage(mContext.getString(R.string.success_valid_email_password));
            mView.navigateTo(email);
        }else{
            mView.showFailedMessage(mContext.getString(R.string.error_valid_email_password));
        }
    }

    @Override
    public void start() {}

}
