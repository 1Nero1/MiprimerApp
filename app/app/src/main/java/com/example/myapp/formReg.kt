package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class formReg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_reg)

        /**Evento para Regresar**/
        val btn_reg: Button = findViewById(R.id.btn_regresar)
        btn_reg.setOnClickListener {
            val ev: Intent = Intent(this,MainActivity:: class.java)
            startActivity(ev)
        }
    }

}