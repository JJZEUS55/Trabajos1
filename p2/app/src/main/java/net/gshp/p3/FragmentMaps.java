package net.gshp.p3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentMaps extends Fragment implements OnMapReadyCallback, android.location.LocationListener {

    private GoogleMap mMap;

    private Geo coordenadas;
    private DBPosicion db;
    private LocationManager locationManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container, false);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        db = new DBPosicion(view.getContext());
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        LatLng trabajo = new LatLng(19.4323372, -99.194773);
        mMap.addMarker(new MarkerOptions().position(trabajo).title("Marcador en el trabajo"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trabajo, 15));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(trabajo));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onLocationChanged(Location location) {
        if (getActivity() != null) {
            Log.d("LOCATION CHANGED", "Entrando al LC");
            coordenadas = new Geo();
            //LocalTime hora = LocalTime.now();
            Date date = new Date();
            Calendar hora = GregorianCalendar.getInstance();
            hora.setTime(date);
            String txtHora = "" + hora.get(Calendar.HOUR_OF_DAY) + " : " + hora.get(Calendar.MINUTE) + " : " + hora.get(Calendar.SECOND);

            // String hora = "1234";
            coordenadas.setLat("" + location.getLatitude());
            coordenadas.setLon("" + location.getLongitude());
            coordenadas.setTime(txtHora);
            db.insertarValores(coordenadas);

            Toast.makeText(getActivity(), "Se han guardado correctamente los datos", Toast.LENGTH_SHORT).show();
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
