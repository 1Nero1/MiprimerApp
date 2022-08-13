package com.example.myapp

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapp.DB.BasedeDatos

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)
        /**Variables**/
        var enteroId: Int = 0;
        /**conectando a la base de datos**/
        val con = BasedeDatos( this, "db_robotic", null,1);
        val baseDatos = con.readableDatabase; //Modo lectura
        /**Mandando a llamar al activiti main**/
        val objetcIntent: Intent = intent;
        var recibir_id =objetcIntent.getStringExtra("idUsuario");
        /**Asignacion**/
        var nombreCompletotxt: TextView = findViewById(R.id.textNomComp);
        var btn_cuenta: Button = findViewById(R.id.btn_cuenta);
        var btn_historial: Button = findViewById(R.id.btn_histo);
        println(recibir_id);
        println(enteroId);
        //Convertir a entero
        if (recibir_id != null) {
            enteroId = recibir_id.toInt()
        };
        println(enteroId);
        //Realizando consulta
        val cr: Cursor = baseDatos.rawQuery("SELECT nom, apPat, apMat " +
                "FROM t_usuario " +
                "WHERE id_usuario = '" + enteroId +"'", null);

        //Recorriendo el cursor
        if(cr.moveToFirst()){
            do {
                //aqui va el text view
                nombreCompletotxt.append( cr.getString(0).toString() + " ");
                nombreCompletotxt.append( cr.getString(1).toString() + " ");
                nombreCompletotxt.append( cr.getString(2).toString());

                println(nombreCompletotxt);

            }while (cr.moveToNext())
        }

        /**Eventos**/
        btn_cuenta.setOnClickListener{
            /**Didigir a nueva ventana**/
        }


    /**Fin*/
    }
}