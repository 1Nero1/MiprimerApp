package com.example.myapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BasedeDatos extends SQLiteOpenHelper {
    /**Nombre de la base de datos**/
    private static final String BaseNombre = "db_robotic.db";

    /**Nombre de la version de la base de datos**/
    private static final int BaseVersion = 1;

    /**Nombre de las tablas**/
    protected static final String tablaUsuario = "t_usuario";
    protected static final String tablaCuenta = "t_cuenta";
    protected static final String tablaHistorial = "t_historial";

    /**Constructor**/
    public BasedeDatos(@Nullable Context context) {
        super(context, BaseNombre, null, BaseVersion);
    }

    /**Donde se crean las tablas**/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + tablaUsuario + "(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres STRING (20)," +
                "apPat STRING (20)," +
                "apMat STRING (20)," +
                "fecha DATEONLY," +
                "correo STRING (30)," +
                "contrasenia STRING (30) )");

        sqLiteDatabase.execSQL("CREATE TABLE " + tablaCuenta + "(" +
                "id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER," +
                "saldo DOUBLE (15)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario) )");

        sqLiteDatabase.execSQL("CREATE TABLE " + tablaHistorial + "(" +
                "id_usuario INTEGER," +
                "id_cuenta INTEGER," +
                "f_transaccion DATEONLY," +
                "m_transaccion DOBLE," +
                "des_movimiento String (150)," +
                "CONSTRAINT pk_idHistorial PRiMARY KEY (id_usuario, id_cuenta)," +
                "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES tablaUsuario(id_usuario)," +
                "CONSTRAINT fk_id_cuenta FOREIGN KEY (id_cuenta) REFERENCES tablaCuenta(id_cuenta) )");

    }

    /**Donce se actualiza la base**/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /**Cuando se cambie la version es cuando aqui se ejecuta los metodos necesarios**/
    }
}
