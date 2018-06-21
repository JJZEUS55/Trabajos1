package net.gshp.p3;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import net.gshp.p3.configuracion.Config;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class BottomActivity extends AppCompatActivity {

    private BroadcastReceiver mRegistroBroadCastReceiver;
    private BottomNavigationView botView;
    private Fragment fragmento, fragmentoMapa, fragmentoLista;
    private FragmentManager fragmentManager;

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistroBroadCastReceiver,
                new IntentFilter("registroCompletado"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistroBroadCastReceiver,
                new IntentFilter(Config.STR_PUSH));

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistroBroadCastReceiver);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bot);
        Stetho.initializeWithDefaults(this);

        mRegistroBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.STR_PUSH)) {
                    String mensaje = intent.getStringExtra("message");
                    showNotificacion("EDMTV", mensaje);
                }
            }
        };

        botView = findViewById(R.id.bottom_navigation);
        botView.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();
        botView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    //Problema siempre van a ser nulos por que el setContentView No tiene los id de los fragmentos
                    case R.id.action_mapa:
                        fragmentoMapa = fragmentManager.findFragmentById(R.id.map);
                        if (fragmentoMapa == null) {
                            fragmentoMapa = new FragmentMaps();
                            fragmento = fragmentoMapa;
                        } else {
                            fragmento = fragmentoMapa;
                        }
                        //fragmento = new FragmentMaps();
                        break;

                    case R.id.action_datosbd:
                        fragmentoLista = fragmentManager.findFragmentById(R.id.lista);
                        if (fragmentoLista == null) {
                            fragmentoLista = new FragmentLista();
                            fragmento = fragmentoLista;
                        } else {
                            fragmento = fragmentoLista;
                        }
                        break;

                    case R.id.action_tablabd:
                        fragmentoMapa = fragmentManager.findFragmentById(R.id.map);
                        if (fragmentoMapa == null) {
                            fragmentoMapa = new FragmentMaps();
                            fragmento = fragmentoMapa;
                        } else {
                            fragmento = fragmentoMapa;
                        }

                        //Toast.makeText(getBaseContext(), "Aun no jala :v", Toast.LENGTH_SHORT).show();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragmento).commit();

                return true;
            }
        });
    }

    private void showNotificacion(String titulo, String mensaje) {
        Intent intent = new Intent(getApplicationContext(), BottomActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext(),  "M_CH_ID");
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());

    }

}
