package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText

class formReg : AppCompatActivity() {
    /**Variables**/
    val btn_reg: Button = findViewById(R.id.btn_regresar);
    var textTextNombres: EditText?=null;
    var textApePat: EditText?=null;
    var textApeMat: EditText?=null;
    var textFecha: EditText?=null;
    var texCorreo: EditText?=null;
    var textContra: EditText?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_reg)

            /**Asignacion**/
        textTextNombres = findViewById(R.id.editTextTextNombres);
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

        fun Registar(view: View){

        }
    }

}