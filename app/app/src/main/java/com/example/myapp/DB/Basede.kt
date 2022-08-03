package com.example.myapp.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Basede(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE t_usuario(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres STRING (20)," +
                "apPat STRING (20)," +
                "apMat STRING (20)," +
                "fecha DATEONLY," +
                "correo STRING (30)," +
                "contrasenia STRING (30))");

        db?.execSQL("CREATE TABLE t_Cuenta(" +
                "id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER," +
                "saldo DOUBLE (15)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario) )");

        db?.execSQL("CREATE TABLE t_Historial(" +
                "id_usuario INTEGER," +
                "id_cuenta INTEGER," +
                "f_transaccion DATEONLY," +
                "m_transaccion DOBLE," +
                "des_movimiento String (150)," +
                "CONSTRAINT pk_idHistorial PRiMARY KEY (id_usuario, id_cuenta)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario)," +
                "CONSTRAINT fk_id_cuenta FOREIGN KEY (id_cuenta) REFERENCES tablaCuenta(id_cuenta) )");

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}