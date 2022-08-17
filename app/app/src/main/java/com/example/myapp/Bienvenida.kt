package com.example.myapp

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapp.DB.BasedeDatos

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)
        /**Variables**/
        var enteroId: Int = 0;

        /**conectando a la base de datos**/
        val con = BasedeDatos( this, "db_robotic", null,1);
        /** val baseDatos = con.readableDatabase; //Modo lectura **/
        val baseDatos = con.writableDatabase; //Modo lectura y escritura

        /**Mandando a llamar al activiti main**/
        val objetcIntent: Intent = intent;
        var recibir_id =objetcIntent.getStringExtra("idUsuario");

        /**Asignacion**/
        var nombreCompletotxt: TextView = findViewById(R.id.textNomComp);
        var btn_cuenta: Button = findViewById(R.id.btn_cuenta);
        var btn_historial: Button = findViewById(R.id.btn_histo);


        //Convertir a entero
        if (recibir_id != null) {
            enteroId = recibir_id.toInt()
        };

        //Realizando consulta
        val cr: Cursor = baseDatos.rawQuery("SELECT nom, apPat, apMat " +
                "FROM t_usuario " +
                "WHERE id_usuario = '" + enteroId +"'", null);

        //Recorriendo el cursor
        if(cr.moveToFirst()){
            do {
                nombreCompletotxt.append( cr.getString(0).toString() + " ");
                nombreCompletotxt.append( cr.getString(1).toString() + " ");
                nombreCompletotxt.append( cr.getString(2).toString());
            }while (cr.moveToNext())
        }

        /**Eventos**/
        btn_cuenta.setOnClickListener{
            /**Didigir a nueva ventana**/

            val cr2: Cursor = baseDatos.rawQuery("SELECT id_cuenta " +
                    "FROM t_Cuenta " +
                    "WHERE id_usuario = '" + enteroId +"'", null);

            if(cr2.moveToFirst()){
                do {
                    var enviarId = cr2.getString(0);
                    val ev: Intent = Intent(this,cuenta:: class.java).apply {
                        putExtra("idCuenta",enviarId)
                    }
                    startActivity(ev)

                }while (cr.moveToNext())
            } else{
                Toast.makeText(this, "NO TIENE CUENTA", Toast.LENGTH_LONG).show();
                //Creando cuenta
                var regCuenta = ContentValues();
                regCuenta.put("id_usuario",enteroId);
                regCuenta.put("saldo",1000.00);

                baseDatos.insert("t_Cuenta",null,regCuenta);

                Toast.makeText(this, "CUENTA CREADA", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Precione nuevamente en CUENTA", Toast.LENGTH_LONG).show();
            }
        }

        btn_historial.setOnClickListener{
            val ev: Intent = Intent(this,historial:: class.java).apply{
                putExtra("idUsuario",recibir_id);
            }
            startActivity(ev);
        }


    /**Fin*/
    }
}