package net.gshp.p3;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by leo on 20/06/18.
 */

public class UsoGeo implements LocationListener {
    private Location location;
    private Geo coordenadas;
    private DBPosicion db;
    private Context context;

    public UsoGeo(Context context) {
        this.context = context;
        this.location = new Location("");
        this.coordenadas = new Geo();
        this.db = new DBPosicion(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            LocalTime hora = LocalTime.now();
            coordenadas.setLat("" + location.getLatitude());
            coordenadas.setLon("" + location.getLongitude());
            coordenadas.setTime(hora.toString());
            db.insertarValores(coordenadas);
            Toast.makeText(context, "Se han guardado correctamente los datos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
