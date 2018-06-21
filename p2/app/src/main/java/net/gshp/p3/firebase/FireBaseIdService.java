package net.gshp.p3.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FireBaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String actualizadoToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistroServidor(actualizadoToken);
    }

    private void sendRegistroServidor(String actualizadoToken) {
        //TODO
    }
}
