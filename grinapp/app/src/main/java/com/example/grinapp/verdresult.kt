package com.example.grinapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.marrobg.*
import kotlinx.android.synthetic.main.verdbg.*

class verdresult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verdbg)
        supportActionBar?.title="Busca un Producte"
        val preferences= getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
        val prod= preferences.getString("result","nono")
        verdText.text=prod.toString()
        verdBack.setOnClickListener {
            finish()
        }


    }
}

