package com.hi_depok.hi_depok;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Azmi Muhammad on 4/9/2017.
 */

public class ServerSide {

    private static ServerSide mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private ServerSide(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized ServerSide getInstance(Context context){
        if(mInstance == null){
            mInstance = new ServerSide(context);
        }
        return mInstance;
    }
    public<T> void addtoRequestQueue(Request<T> respone){
        mRequestQueue.add(respone);
    }
}
