package com.example.myapp.DB

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BasedeDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE t_usuario(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom STRING (20)," +
                "apPat STRING (20)," +
                "apMat STRING (20)," +
                "fec DATEONLY," +
                "corr STRING (30)," +
                "contra STRING (30))");

        db?.execSQL("CREATE TABLE t_Cuenta(" +
                "id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER," +
                "saldo DOUBLE (13,2)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario) )");

        db?.execSQL("CREATE TABLE t_Historial(" +
                "id_usuario INTEGER," +
                "id_cuenta INTEGER," +
                "f_transaccion DATEONLY," +
                "m_transaccion DOBLE (13,2)," +
                "des_movimiento String (150)," +
                "CONSTRAINT pk_idHistorial PRiMARY KEY (id_usuario, id_cuenta)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario)," +
                "CONSTRAINT fk_id_cuenta FOREIGN KEY (id_cuenta) REFERENCES tablaCuenta(id_cuenta) )");

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

//    Buscar usuarios
    fun  login(usu: String, pass: String): Int {
        val a=0;
        var baseDatos = writableDatabase;
        val cr: Cursor = baseDatos.rawQuery("SELECT * FROM t_usuario WHERE corr = '"+ usu + "' AND contra = '" + pass +"'", null);
        if (cr.moveToFirst()){
            do {
                a+1;
            }while (cr.moveToFirst());
        }
        return a;
    }
}