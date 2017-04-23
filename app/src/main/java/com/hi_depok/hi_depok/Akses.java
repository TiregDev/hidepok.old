package com.hi_depok.hi_depok;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Azmi Muhammad on 4/9/2017.
 */

public class Akses {

    private static Akses mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private Akses(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized Akses getInstance(Context context){
        if(mInstance == null){
            mInstance = new Akses(context);
        }
        return mInstance;
    }
    public<T> void addtoRequestQueue(Request<T> respone){
        mRequestQueue.add(respone);
    }
}
