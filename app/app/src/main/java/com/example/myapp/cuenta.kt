package com.example.myapp

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.myapp.DB.BasedeDatos

class cuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
        /**conectando a la base de datos**/
        val con = BasedeDatos( this, "db_robotic", null,1);
        val baseDatos = con.writableDatabase;

        /**Variables**/
        var usuarioId: Int = 0;
        var cuentaId: Int = 0;

        /**Asignacion**/
        var nombreCompletotxt: TextView = findViewById(R.id.textViewNombre);
        var noCuentatxt: TextView = findViewById(R.id.textViewNoCuenta);
        var saldotxt: TextView = findViewById(R.id.textViewSaldo);

            /**Mandando a llamar al activiti Bienvenida**/
        val bienvenidaIntent: Intent = intent;
        var cuentaRec_id = bienvenidaIntent.getStringExtra("idCuenta");

        //Convertir a entero
        if (cuentaRec_id != null) {
            cuentaId = cuentaRec_id.toInt();
        };

        //Realizando consulta
        val cr: Cursor = baseDatos.rawQuery("SELECT cu.id_usuario, cu.saldo, usu.nom, usu.apPat, usu.apMat " +
                "FROM t_Cuenta cu, t_usuario usu " +
                "WHERE cu.id_usuario = usu.id_usuario" +
                "   AND cu.id_cuenta = '" + cuentaId +"'", null);

        //Recorriendo el cursor
        if(cr.moveToFirst()){
            do {
                noCuentatxt.setText("Numero de cuenta: " + cr.getString(0) +"")
                saldotxt.setText("Saldo: " + cr.getString(1) +"")
                nombreCompletotxt.append( cr.getString(2).toString() + " ");
                nombreCompletotxt.append( cr.getString(3).toString() + " ");
                nombreCompletotxt.append( cr.getString(4).toString());
            }while (cr.moveToNext())
        }
        else{
            Toast.makeText(this, "ALGO SALIO MAL", Toast.LENGTH_LONG).show();
        }
        /**Eventos**/
    /**Fin**/
    }
}