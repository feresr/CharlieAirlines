package com.southwest.southwestapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;


/**
 * Created by emiliano.gudino on 09/09/2015.
 */
public class DialogManager {

    public void showDialog(final Activity origin, final String title, final String msj, final String positive,
                           final DialogInterface.OnClickListener positiveListener, final String negative,
                           final DialogInterface.OnClickListener negativeListener, final boolean isCancelable) {

        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                builder.setTitle(title);
                builder.setMessage(msj);
                builder.setCancelable(false);
                builder.setPositiveButton(positive, positiveListener);
                builder.setNegativeButton(negative, negativeListener);
                AlertDialog dialog = builder.show();
                dialog.setCancelable(isCancelable);
                TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
                if (messageText != null) {
                    messageText.setGravity(Gravity.START);
                }
            }

        });
    }

    private AlertDialog.Builder getBuilder(Activity origin) {
        return new AlertDialog.Builder(origin);
    }

}