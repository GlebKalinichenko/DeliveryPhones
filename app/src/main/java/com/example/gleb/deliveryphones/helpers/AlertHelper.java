package com.example.gleb.deliveryphones.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.example.gleb.deliveryphones.events.SendPhonesEvent;
import org.greenrobot.eventbus.EventBus;

public class AlertHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private Context context;
    private static AlertHelper instance = null;

    public static AlertHelper getInstance(Context context) {
        if (instance == null){
            instance = new AlertHelper(context);
        }
        return instance;
    }

    private AlertHelper(Context context) {
        this.context = context;
    }

    /**
    * Initialize of alert dialog
    * @param styleResId        Id of style for alert dialog
    * @return AlertDialog      Created alert dialog
    * */
    public AlertDialog createDialog(int styleResId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, styleResId);
        builder.setTitle(R.string.alert_conf_title);
        builder.setMessage(R.string.alert_conf_content);

        String sendContacts = context.getString(R.string.confirm_send_contacts);
        String receiveContacts = context.getString(R.string.receive_contacts);

        builder.setPositiveButton(sendContacts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EventBus.getDefault().post(new SendPhonesEvent());
            }
        });
        builder.setNegativeButton(receiveContacts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EventBus.getDefault().post(new ReceivePhonesEvent());
            }
        });

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
