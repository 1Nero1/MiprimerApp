package com.example.myapp

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.example.myapp.DB.BasedeDatos

class historial : AppCompatActivity() {

    /**Variables**/
    var tableHistorial: TableLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        /**Variables**/
        var enteroId: Int = 0;

        /**Asignacion**/
        tableHistorial = findViewById(R.id.tl_historial);
            /**Mandando a llamar al activiti main**/
        val objetcIntent: Intent = intent;
        var recibir_id =objetcIntent.getStringExtra("idUsuario");

        //Convertir a entero
        if (recibir_id != null) {
            enteroId = recibir_id.toInt()
        };

        /**Eventos**/
        cargarTabla(enteroId);

    /**FIN**/
    }

    fun cargarTabla(enteroId: Int){
        //Borra el contenido
        tableHistorial?.removeAllViews();

        /**conectando a la base de datos**/
        val con = BasedeDatos( this, "db_robotic", null,1);
        /** val baseDatos = con.readableDatabase; //Modo lectura **/
        val baseDatos = con.writableDatabase; //Modo lectura y escritura

        val conHistorial: Cursor = baseDatos.rawQuery("SELECT id_usuario, id_cuenta, " +
                "f_transaccion, m_transaccion, des_movimiento " +
                "FROM t_Historial " +
                "WHERE id_usuario = '" + enteroId +"'",null);

        if(conHistorial.moveToFirst()){
            do {
                           // layoutInflater rellenara los campos
            val registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout_his,null,false);

            val usuario =registro.findViewById<View>(R.id.tvUsuario) as TextView;
            val cuenta =registro.findViewById<View>(R.id.tvCuenta) as TextView;
            val fecha =registro.findViewById<View>(R.id.tvFecha) as TextView;
            val monto =registro.findViewById<View>(R.id.tvMonto) as TextView;
            val des =registro.findViewById<View>(R.id.tvDescripcion) as TextView;

            /**Asignacion**/

            usuario.setText(conHistorial.getString(0));
            cuenta.setText(conHistorial.getString(1));
            fecha.setText(conHistorial.getString(2));
            monto.setText(conHistorial.getString(3));
            des.setText(conHistorial.getString(4));

            tableHistorial?.addView(registro);

            }while (conHistorial.moveToNext());
        }else{
            Toast.makeText(this, "Aun no tiene historial", Toast.LENGTH_LONG).show();
        }
    }
}