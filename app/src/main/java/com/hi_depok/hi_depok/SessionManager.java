package com.hi_depok.hi_depok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hi_depok.hi_depok.Activity_Main.login;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "HiDepok";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    //ID
    public static final String KEY_ID_USER = "id_user";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // User name (make variable public to access from outside)
    public static final String KEY_USERNAME = "username";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASS = "pass";

    public static final String KEY_BIO = "bio";

    public static final String KEY_NOTELP = "notelp";

    public static final String KEY_FOTO = "foto";

    public static final String KEY_ALAMAT = "alamat";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session INI
     */
    public void createLoginSession(String id_user, String username, String email, String pass, String name,
                                   String bio, String notelp, String avatar, String alamat) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing id in pref
        editor.putString(KEY_ID_USER, id_user);

        // Storing username in pref
        editor.putString(KEY_USERNAME, username);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // Storing password in pref
        editor.putString(KEY_PASS, pass);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

//        // Storing bio in pref
        editor.putString(KEY_BIO, bio);
//
//        // Storing notelp in pref
        editor.putString(KEY_NOTELP, notelp);

        editor.putString(KEY_FOTO, avatar);

        editor.putString(KEY_ALAMAT, alamat);

        // commit changes
        editor.commit();
    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        // id
        user.put(KEY_ID_USER, pref.getString(KEY_ID_USER, null));

        // username
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user pass id
        user.put(KEY_PASS, pref.getString(KEY_PASS, null));

        //user name id
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

//        // user bio id
        user.put(KEY_BIO, pref.getString(KEY_BIO, null));
//
//        //user notelp id
        user.put(KEY_NOTELP, pref.getString(KEY_NOTELP, null));

        user.put(KEY_FOTO, pref.getString(KEY_FOTO, null));

        user.put(KEY_ALAMAT, pref.getString(KEY_ALAMAT, null));

        // return user
        return user;
    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
