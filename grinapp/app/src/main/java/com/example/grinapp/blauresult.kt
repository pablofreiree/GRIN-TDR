package com.example.grinapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.blaubg.*
import kotlinx.android.synthetic.main.grocbg.*
import kotlinx.android.synthetic.main.verdbg.*

class blauresult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blaubg)
        supportActionBar?.title="Busca un Producte"
        val preferences= getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
        val prod= preferences.getString("result","nono")
        blauText.text=prod.toString()

        blauBack.setOnClickListener {
            finish()
        }
    }
}