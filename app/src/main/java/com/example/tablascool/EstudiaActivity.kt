package com.example.tablascool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_estudia.*
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import java.util.*

class EstudiaActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AdapterView.OnItemClickListener {
    var tts: TextToSpeech? = null
    var listaElementos:MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudia)

        listaElementos= mutableListOf<String>()
        tts= TextToSpeech(this , this)
        Log.i("Lenguajes", Locale.getAvailableLocales().toString())

        listaTablas.setOnItemClickListener(this)

        btnRegresar.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                listaElementos!!.clear()
                if (progress < 1){
                    if (progress == 0) {
                        seekBar!!.setProgress(1)
                    }
                }

                if (progress > 0) {
                    for (i in 1..10) {
                        val texto = "$progress x $i = ${progress * i} "
                        listaElementos!!.add(texto)
                    }
                    val adaptador = ArrayAdapter(
                        application,
                        android.R.layout.simple_list_item_1, listaElementos!!)
                    listaTablas.adapter = adaptador
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }
    override fun onInit(status: Int) {
        if (status==TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if(result != TextToSpeech.LANG_NOT_SUPPORTED && result != TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "Todo ok!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Lenguaje no soportado", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroy(){
        if(tts!! !=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
           var textoALeer= listaElementos!!.get(position)
           tts!!.speak(textoALeer,TextToSpeech.QUEUE_FLUSH, null, null )

    }
}