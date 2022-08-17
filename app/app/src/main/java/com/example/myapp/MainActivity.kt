package com.example.myapp

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapp.DB.BasedeDatos

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**Variables**/
        val btn_ingresar: Button = findViewById(R.id.Ingresar);
        val btn_registrar: Button = findViewById(R.id.Registrar);
        var textCorreo: EditText? = null;
        var textContra: EditText? = null;

        /**Asignacion**/
        textCorreo = findViewById(R.id.editTextCorreo);
        textContra = findViewById(R.id.editTextPass);

        /**Metodo para los botones**/
            /**Evento para Registrarse**/
        btn_registrar.setOnClickListener {
            val ev: Intent = Intent(this,formReg:: class.java)
            startActivity(ev)
        }
            /**Evento para consultar**/
        btn_ingresar.setOnClickListener{
            /**conectando a la base de datos**/
            val con = BasedeDatos( this, "db_robotic", null,1);
            val baseDatos = con.writableDatabase;

            /**Asignar variables**/
            val corr = textCorreo?.text.toString();
            val contra = textContra?.text.toString();

            if(corr.isEmpty() == false || contra.isEmpty() == false){
                val cr: Cursor = baseDatos.rawQuery("SELECT id_usuario FROM t_usuario WHERE corr = '"+ corr + "' AND contra = '" + contra +"'", null);
                if (cr.moveToFirst()){
                    do {
                        var enviarId = cr.getString(0);
                        val ev: Intent = Intent(this,Bienvenida:: class.java).apply{
                            putExtra("idUsuario",enviarId);
                        }
                        startActivity(ev)
                    }while (cr.moveToNext());
                    //Limpiar
                    textCorreo?.setText("");
                    textContra?.setText("");
                }else{
                    Toast.makeText(this, "La contraseña o el correo son incorrectos", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this, "LLene todos los campos", Toast.LENGTH_LONG).show();
            }
        }
        /**FIN**/
    }

}