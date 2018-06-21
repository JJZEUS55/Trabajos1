package net.gshp.p3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBLista extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NOMBRE = "listaDB5.db";

    private static final String TABLA1_NOMBRE = "c_sku";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_VALUE = "valor";

    private static final String TABLA2_NOMBRE = "answer_sku";
    private static final String COLUMNA_ANSWER = "answer";
    private static final String COLUMNA_ID_SKU = "id_sku";


    public DBLista(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
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


    public List<Csku> selectAllDatos(int opc) {
        List<Csku> lista = new ArrayList<>();
        String query = "";

        switch (opc) {
            case 0:
                query = "SELECT * FROM " + TABLA1_NOMBRE;
                break;

            case 1:
                query = "SELECT * FROM " + TABLA2_NOMBRE;
                break;
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Csku auxSku = new Csku();
            auxSku.setIdCSKU(cursor.getInt(0));
            auxSku.setValue(cursor.getString(1));
            lista.add(auxSku);

        }
        Log.d("Base", "Lista" + lista.toString());
        cursor.close();
        db.close();
        return lista;
    }

    public void actualizarDatosTabla2(Csku datos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valoresActualizar = new ContentValues();
        valoresActualizar.put(COLUMNA_ANSWER, datos.getValue());

        db.update(TABLA2_NOMBRE, valoresActualizar, COLUMNA_ID + "=" + datos.getIdCSKU(), null);
    }

    public boolean isDatosTabla2(){
        boolean conValor = false;
        String dato = "";
        String query = "SELECT * FROM " + TABLA2_NOMBRE
                + " ASC LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            dato = cursor.getString(1);
            if(dato != "0"){
                conValor = true;
            }else{
                conValor = false;
            }
        }
        return conValor;
    }

}
