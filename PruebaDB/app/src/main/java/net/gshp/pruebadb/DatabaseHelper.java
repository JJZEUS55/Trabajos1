package net.gshp.pruebadb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leo on 5/07/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prueba1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA1_NOMBRE = "c_sku";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_VALUE = "valor";

    private static final String TABLA2_NOMBRE = "answer_sku";
    private static final String COLUMNA_ANSWER = "answer";
    private static final String COLUMNA_ID_SKU = "id_sku";

    private static DatabaseHelper databaseHelper;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTablaCsku = "CREATE TABLE " + TABLA1_NOMBRE + " ("
                + COLUMNA_ID + " INTEGER PRIMARY KEY, "
                + COLUMNA_VALUE + " TEXT" + ")";

        String crearTablaAnswerSku = "CREATE TABLE " + TABLA2_NOMBRE
                + " (" + COLUMNA_ID + " INTEGER PRIMARY KEY, "
                + COLUMNA_ANSWER + " TEXT, "
                + COLUMNA_ID_SKU + " INTEGER, "
                + "FOREIGN KEY " + "(" + COLUMNA_ID_SKU + ") " + " REFERENCES " + " C_SKU(ID)" + " )";

        db.execSQL(crearTablaCsku);
        db.execSQL(crearTablaAnswerSku);
        ContentValues valores = new ContentValues();

        for (int i = 0; i < 20; i++) {
            valores.put(COLUMNA_ID, i);
            valores.put(COLUMNA_VALUE, "0");
            db.insert(TABLA1_NOMBRE, null, valores);
        }

        valores = new ContentValues();
        for (int i = 0; i < 20; i++) {
            valores.put(COLUMNA_ID, i);
            valores.put(COLUMNA_ANSWER, "0");
            valores.put(COLUMNA_ID_SKU, i);
            db.insert(TABLA2_NOMBRE, null, valores);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
