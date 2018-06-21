package net.gshp.p3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leo on 20/06/18.
 */

public class DBPosicion extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NOMBRE = "geoDB.db";

    private static final String TABLA_GEO = "geoloc";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_LAT = "lat";
    private static final String COLUMNA_LON = "lon";
    private static final String COLUMNA_TIME = "time";


    public DBPosicion(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "CREATE TABLE " + TABLA_GEO
                + " ( " + COLUMNA_ID + " INTEGER PRIMARY KEY, "
                + COLUMNA_LAT + " TEXT, "
                + COLUMNA_LON + " TEXT, "
                +COLUMNA_TIME + " TEXT " + " )";

        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertarValores(Geo geolocal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        //valores.put(COLUMNA_ID, geolocal.getId());
        valores.put(COLUMNA_LAT, geolocal.getLat());
        valores.put(COLUMNA_LON, geolocal.getLon());
        valores.put(COLUMNA_TIME, geolocal.getTime());
        db.insert(TABLA_GEO, null, valores);
    }
}
