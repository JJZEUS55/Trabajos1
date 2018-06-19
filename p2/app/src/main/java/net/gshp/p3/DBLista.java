package net.gshp.p3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBLista extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NOMBRE = "listaDB1.db";
    private static final String TABLA1_NOMBRE = "C_SKU";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_VALUE = "value";


    public DBLista(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "CREATE TABLE " + TABLA1_NOMBRE + "(" + COLUMNA_ID + "INTEGER PRIMARY KEY, " + COLUMNA_VALUE + "TEXT" + ")";
        db.execSQL(crearTabla);
        //String insertarValores = "INSERT INTO " + TABLA1_NOMBRE + "(" + COLUMNA_VALUE + ")" + " VALUES " + "(0)";
        ContentValues valores = new ContentValues();

        for (int i = 0; i < 20; i++) {
            valores.put(COLUMNA_ID, i);
            valores.put(COLUMNA_VALUE, "0");
            db.insert(TABLA1_NOMBRE, null, valores);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public List<Csku> cargarDatos() {
        List<Csku> lista = new ArrayList<>();
        Csku auxSku = new Csku();

        String query = "SELECT * FROM " + TABLA1_NOMBRE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            auxSku.setIdCSKU(cursor.getInt(0));
            auxSku.setValue(cursor.getString(1));
            lista.add(auxSku);
        }
        cursor.close();
        db.close();
        return lista;
    }

}
