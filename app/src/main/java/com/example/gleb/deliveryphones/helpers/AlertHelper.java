package com.example.gleb.deliveryphones.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.gleb.deliveryphones.R;
import com.example.gleb.deliveryphones.events.GoogleAuthEvent;
import com.example.gleb.deliveryphones.events.ReceivePhonesEvent;
import com.example.gleb.deliveryphones.events.SendPhonesEvent;
import com.google.android.gms.common.SignInButton;

import org.greenrobot.eventbus.EventBus;

public class AlertHelper {
    private final String LOG_TAG = this.getClass().getCanonicalName();

    /**
    * Initialize of alert dialog
    * @param styleResId        Id of style for alert dialog
    * @return AlertDialog      Created alert dialog
    * */
    public static AlertDialog createDialog(Context context, int styleResId){
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

    public static AlertDialog createDialog(Context context, int viewResId, int styleResId){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(viewResId, null);

        SignInButton signInButton = (SignInButton) view.findViewById(R.id.auth_button);
        signInButton.setOnClickListener(i -> EventBus.getDefault().post(new GoogleAuthEvent()));

        AlertDialog.Builder builder = new AlertDialog.Builder(context, styleResId);
        builder.setView(view);
        builder.setTitle(R.string.alert_conf_title);
        builder.setMessage(R.string.alert_conf_content);

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
