package com.example.myapp.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BasedeDatosJava extends SQLiteOpenHelper {

    public static final String nombreBaseDatos = "dbJava";

    public BasedeDatosJava(Context context) {
        super(context, "dbJava", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE t_usuario(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom STRING (20)," +
                "apPat STRING (20)," +
                "apMat STRING (20)," +
                "fec DATEONLY," +
                "corr STRING (30)," +
                "contra STRING (30))");

        db.execSQL("CREATE TABLE t_Cuenta(" +
                "id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER," +
                "saldo DOUBLE (15)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario) )");

        db.execSQL("CREATE TABLE t_Historial(" +
                "id_usuario INTEGER," +
                "id_cuenta INTEGER," +
                "f_transaccion DATEONLY," +
                "m_transaccion DOBLE," +
                "des_movimiento String (150)," +
                "CONSTRAINT pk_idHistorial PRiMARY KEY (id_usuario, id_cuenta)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario)," +
                "CONSTRAINT fk_id_cuenta FOREIGN KEY (id_cuenta) REFERENCES tablaCuenta(id_cuenta) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public boolean ingresarUsuario(){
//
//    }

    public int consultarUsuario(String usu, String pass){
        int a=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM t_usuario", null);
        if (cr != null && cr.moveToFirst()){
            do {
                if (cr.getString(1).equals(usu) && cr.getString(2).equals(pass)){
                    a++;
                }
            }while (cr.moveToFirst());
        }
        return a;
    }
}
