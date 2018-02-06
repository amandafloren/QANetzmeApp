package com.example.amanda.qanetzme.data.sqlite;

import android.content.ContentValues;
import android.content.Context;

import com.example.amanda.qanetzme.data.model.User;

/**
 * Created by amanda on 31/01/18.
 */

public class SqliteController {

    private SqliteHelper sqliteHelper;
    Context context;

    public SqliteController(Context mContext){
        this.context = mContext;
        sqliteHelper = new SqliteHelper(context);

    }

    public boolean saveUserData(User user){
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COL_USER_FNAME, user.getUser_fname());
        values.put(SqliteTable.COL_USER_LNAME, user.getUser_lname());
        values.put(SqliteTable.COL_USER_ADDRESS, user.getUser_address());
        values.put(SqliteTable.COL_USER_PHONE, user.getUser_phone());
        values.put(SqliteTable.COL_USER_EMAIL, user.getUser_email());
        values.put(SqliteTable.COL_USER_PASSWORD, user.getUser_password());
        return sqliteHelper.insertData(SqliteTable.TABLE_USER, values);
    }

    public int updateUserData(User user, String email){
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COL_USER_FNAME, user.getUser_fname());
        values.put(SqliteTable.COL_USER_LNAME, user.getUser_lname());
        values.put(SqliteTable.COL_USER_ADDRESS, user.getUser_address());
        values.put(SqliteTable.COL_USER_PHONE, user.getUser_phone());
        values.put(SqliteTable.COL_USER_EMAIL, user.getUser_email());
        values.put(SqliteTable.COL_USER_PASSWORD, user.getUser_password());
        return sqliteHelper.updateData(SqliteTable.TABLE_USER, values, email);
    }

    public boolean checkUserCredentials(String email, String password){
        return sqliteHelper.checkUser(email, password);
    }

    public User getUserDetails(String email){
        return sqliteHelper.getUserDetail(email);
    }

    public boolean deleteAccount(String email){
        return sqliteHelper.deleteUser(email);
    }
}
