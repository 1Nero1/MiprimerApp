package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
                                        /** ! Sirve apara que todos los componentes sirvan sin tener que hacer
                                        //      mucho codigo, en este caso para que todos los botones tengan
                                        //      la misma accion de onclivk **/
// class MainActivity : AppCompatActivity(), View.onClickListener() {
class MainActivity : AppCompatActivity(){
    /**Variables Globales**/
//    var boton: Button ? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**Evento para Registrarse**/
        val btn: Button = findViewById(R.id.Registrar)
        btn.setOnClickListener {
            val ev: Intent = Intent(this,formReg:: class.java)
            startActivity(ev)
        }

    }
    /**Metodo para los botones**/

    /** llamando para registro
    fun btnRegistrar(view: View) {
        // Do something in response to button click
    }
     */
}