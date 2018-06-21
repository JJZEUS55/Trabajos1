package net.gshp.p3;

import android.Manifest;
import android.content.Intent;
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
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class BottomActivity extends AppCompatActivity {
    private BottomNavigationView botView;
    private Fragment fragmento;
    private FragmentManager fragmentManager;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bot);
        Stetho.initializeWithDefaults(this);

        botView = findViewById(R.id.bottom_navigation);
        botView.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();
        botView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_mapa:
                        fragmento = new FragmentMaps();
                        break;

                    case R.id.action_datosbd:
                        fragmento = new FragmentLista();
                        break;

                    case R.id.action_tablabd:
                        fragmento = new FragmentMaps();
                        //startService(new Intent(this, UsoGeo.class));
                        //UsoGeo localizacion = new UsoGeo(getBaseContext());


                        Toast.makeText(getBaseContext(), "Aun no jala :v", Toast.LENGTH_SHORT).show();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragmento).commit();

                return true;
            }
        });
    }

}
