package com.example.grinapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.marrobg.*
import kotlinx.android.synthetic.main.puntbg.*
import kotlinx.android.synthetic.main.verdbg.*

class marroresult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marrobg)
        supportActionBar?.title="Busca un Producte"

        val preferences= getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
        val prod= preferences.getString("result","nono")
        marroText.text=prod.toString()
        marroBack.setOnClickListener {
            finish()
        }
    }
}