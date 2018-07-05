package net.gshp.pruebadb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TABLA1_NOMBRE = "c_sku";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_VALUE = "valor";

    private static final String TABLA2_NOMBRE = "answer_sku";
    private static final String COLUMNA_ANSWER = "answer";
    private static final String COLUMNA_ID_SKU = "id_sku";
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withFolder(getCacheDir())
                                .withMetaTables()
                                .withDescendingOrder()
                                .withLimit(100000)
                                .build())
                        .build());

        databaseHelper = DatabaseHelper.getDatabaseHelper(getApplicationContext());
        insertarValores1();
        insertarValores2();

    }

    public void insertarValores1() {
        new Thread(){
            public void run() {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.beginTransaction();
                ContentValues valores = new ContentValues();
                for (int i = 0; i < 2000; i++) {
                    valores.put(COLUMNA_VALUE, "val1: " + i);
                    db.insert(TABLA1_NOMBRE, null, valores);
                }
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }.start();
    }

     public void insertarValores2() {
         new Thread() {
             public void run() {
                 SQLiteDatabase db = databaseHelper.getWritableDatabase();
                 db.beginTransaction();
                 ContentValues valores = new ContentValues();
                 for (int i = 0; i < 6000; i++) {
                     valores.put(COLUMNA_VALUE, "val2: " + i);
                     db.insert(TABLA1_NOMBRE, null, valores);
                 }
                 db.setTransactionSuccessful();
                 db.endTransaction();
             }
         }.start();
    }

}
