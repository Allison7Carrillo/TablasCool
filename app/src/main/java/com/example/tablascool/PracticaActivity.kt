package com.example.tablascool

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_estudia.*
import kotlinx.android.synthetic.main.activity_practica.*
import kotlin.random.Random

class PracticaActivity : AppCompatActivity() {
    var resultado=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica)

        btnVolver.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        generaOperacion()

        btnVerificar.setOnClickListener {
            val resText=etRespuesta.text.toString()
            if (!resText.equals("")){
                val respuesta = resText.toInt()
            if(respuesta == resultado){
                crearDialogoOk()
            }
            else{
               crearDialogoNoOk()

            }
            }
            generaOperacion()
        }

    }
    fun crearDialogoOk(){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialogo_ok, null )
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            .setTitle("MUY BIEN!!")

        val miDialogoOk = mBuilder.create()
        miDialogoOk.show()

        val miPlayer:MediaPlayer? = MediaPlayer.create(this , R.raw.sonidos_mal)
        miPlayer?.start()
    }
    fun crearDialogoNoOk(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialogo_mal, null )
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            .setTitle("SIGUE PRACTICANDO!!")

        val miDialogoNoOk = mBuilder.create()
        miDialogoNoOk.show()

        val miPlayer:MediaPlayer = MediaPlayer.create(this , R.raw.sonido_bien)
        miPlayer?.start()
    }
    fun generaOperacion(){
        val operando1 = Random.nextInt(1,10)
        val operando2 = Random.nextInt(1,10)
        resultado= operando1*operando2
        tvPregunta.text = "$operando1 x $operando2= ?"
        etRespuesta.text.clear()
    }
}