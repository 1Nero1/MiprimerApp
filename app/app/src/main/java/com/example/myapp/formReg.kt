package com.example.myapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapp.DB.BasedeDatos
import java.sql.SQLData

class formReg : AppCompatActivity() {
    /**Variables**/
    val btn_reg: Button = findViewById(R.id.btn_regresar);
    var textNombres: EditText?=null;
    var textApePat: EditText?=null;
    var textApeMat: EditText?=null;
    var textFecha: EditText?=null;
    var texCorreo: EditText?=null;
    var textContra: EditText?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_reg)

            /**Asignacion**/
        textNombres = findViewById(R.id.editTextTextNombres);
        textApePat = findViewById(R.id.editTextApePat);
        textApeMat = findViewById(R.id.editTextApeMat);
        textFecha = findViewById(R.id.editTextFecha);
        texCorreo = findViewById(R.id.editTexCorreo);
        textContra = findViewById(R.id.editTextContra);

        /**Validacion**/

        /**Eventos**/
            /**Evento para Regresar**/

        btn_reg.setOnClickListener {
            val ev: Intent = Intent(this,MainActivity:: class.java)
            startActivity(ev)
        }

        fun insertar(view: View){
            /**conectando a la base de datos**/
            var con = BasedeDatos( this, "db_robotic", null,1);
            var baseDatos = con.writableDatabase;
                // El "?" despues del nombre de la variable es para que no vaya un campo vacio
            var nom = textNombres?.text.toString();
            var apPat = textApePat?.text.toString();
            var apMat = textApeMat?.text.toString();
            var fec = textFecha?.text.toString();
            var corr = texCorreo?.text.toString();
            var contra = textContra?.text.toString();

            /**Validar**/
            if(nom.isEmpty() == false && apPat.isEmpty() == false && apMat.isEmpty() == false && fec.isEmpty() == false && corr.isEmpty() == false && contra.isEmpty() == false){
                var registro = ContentValues()

                    registro.put("nom",nom);
                    registro.put("apPat",apPat);
                    registro.put("apMat",apMat);
                    registro.put("fec",fec);
                    registro.put("corr",corr);
                    registro.put("contra",contra);
                // insertar al a base de datos
                baseDatos.insert("t_usuario", null, registro);

                // limpaiar los input
                textNombres?.setText("");
                textApePat?.setText("");
                textApeMat?.setText("");
                textFecha?.setText("");
                texCorreo?.setText("");
                textContra?.setText("");
                
                // Mostar mensaje
                Toast.makeText(this, "Se registro exitosamente", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "LLene todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

}