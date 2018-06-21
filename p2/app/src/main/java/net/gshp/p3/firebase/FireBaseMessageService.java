package net.gshp.p3.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import net.gshp.p3.configuracion.Config;

public class FireBaseMessageService  extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        mantenerNotificacion(remoteMessage.getNotification().getBody());
    }

    private void mantenerNotificacion(String body) {
        Intent pushNotificacion = new Intent(Config.STR_PUSH);
        pushNotificacion.putExtra("message", body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotificacion);
    }
}
