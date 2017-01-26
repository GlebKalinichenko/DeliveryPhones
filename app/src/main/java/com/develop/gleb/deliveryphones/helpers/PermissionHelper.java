package com.develop.gleb.deliveryphones.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.develop.gleb.deliveryphones.R;
import com.develop.gleb.deliveryphones.events.AllowPermissionEvent;

import org.greenrobot.eventbus.EventBus;

public class PermissionHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private Activity context;
    private static PermissionHelper instance = null;
    private final String WRITE_PERMISSION = Manifest.permission.READ_CONTACTS;
    private final String READ_PERMISSION = Manifest.permission.WRITE_CONTACTS;
    private final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final static int READ_REQUEST = 1;

    public static PermissionHelper getInstance(Activity context) {
        if (instance == null){
            instance = new PermissionHelper(context);
        }

        return instance;
    }

    private PermissionHelper(Activity context) {
        this.context = context;
    }

    public void checkPermissions(){
        if (ContextCompat.checkSelfPermission(context,READ_PERMISSION) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(context,READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, READ_PERMISSION)){
                Log.d(LOG_TAG, "Permission again");
                AlertDialog dialog = AlertHelper.createDialogOnlyMessage(context, R.string.permission_message);

                String ok = StringHelper.getString(context, R.string.ok_dialog);
                String cancel = StringHelper.getString(context, R.string.cancel_dialog);

                dialog.setButton(DialogInterface.BUTTON_POSITIVE, ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(context, new String[]{READ_PERMISSION}, READ_REQUEST);
                    }
                });

                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.finish();
                    }
                });

                if(!((Activity) context).isFinishing()) {
                    dialog.show();
                }
            }
            else{
                ActivityCompat.requestPermissions(context, new String[]{READ_PERMISSION, READ_EXTERNAL_STORAGE}, READ_REQUEST);
            }
        }
        else{
            EventBus.getDefault().post(new AllowPermissionEvent());
        }
    }
}
