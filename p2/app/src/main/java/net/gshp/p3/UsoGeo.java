package net.gshp.p3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by leo on 20/06/18.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class UsoGeo extends Service {
    private Location location;
    private LocationManager mLocationManager;
    private Geo coordenadas;
    private DBPosicion db;
    private Context context;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;

    public class LocationListener implements android.location.LocationListener {

        private Location mUltimaLocalizacion;


        //@RequiresApi(api = Build.VERSION_CODES.O)
        public LocationListener(String prov) {
            mUltimaLocalizacion = new Location(prov);
            //LocalTime hora = LocalTime.now();
            String hora = "123";
            coordenadas.setLat("" + location.getLatitude());
            coordenadas.setLon("" + location.getLongitude());
            coordenadas.setTime(hora.toString());
            db.insertarValores(coordenadas);
            Toast.makeText(context, "Se han guardado correctamente los datos", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLocationChanged(Location location) {
            mUltimaLocalizacion.set(location);

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

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    public UsoGeo(Context context) {
        this.context = context;
        this.location = new Location("");
        this.coordenadas = new Geo();
        this.db = new DBPosicion(context);
    }

    public UsoGeo() {
        this.location = new Location("");
        this.coordenadas = new Geo();
        this.db = new DBPosicion(context);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i("GEO", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("GEO", "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i("GEO", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("GEO", "gps provider does not exist " + ex.getMessage());
        }

    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}

