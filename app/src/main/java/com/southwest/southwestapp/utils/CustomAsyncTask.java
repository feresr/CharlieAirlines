package com.southwest.southwestapp.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by armando.dominguez on 10/09/2015.
 */
public class CustomAsyncTask extends AsyncTask<Void,Void,Void>{

    private Context mContext;
    private CustomProgress mProgress;

    public CustomAsyncTask(Context context){
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgress = new CustomProgress(this.mContext, R.style.dialogFullScreen);
        mProgress.show();

    }

    @Override
    protected Void doInBackground(Void... params) {
        try { Thread.sleep(2000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mProgress.dismiss();
    }
}
