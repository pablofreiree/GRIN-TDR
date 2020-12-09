package com.example.grinapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.grocbg.*
import kotlinx.android.synthetic.main.marrobg.*
import kotlinx.android.synthetic.main.verdbg.*

class grocresult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grocbg)
        supportActionBar?.title="Busca un Producte"
        val preferences= getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
        val prod= preferences.getString("result","nono")
        grocText.text=prod.toString()
        grocBack.setOnClickListener {
            finish()
        }


    }
}