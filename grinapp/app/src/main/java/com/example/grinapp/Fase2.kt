package com.example.grinapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.ArrayAdapter
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fase_2_layout.*
import kotlinx.android.synthetic.main.grisbg.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.URL
import java.sql.Struct
import java.util.*
import kotlin.collections.ArrayList


class Fase2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fase_2_layout)
        supportActionBar?.title="Busca un Producte"

//pillar json de internet i posarlo en taula d'objecte "Product"
        doAsync {

            val json =
                URL("https://script.googleusercontent.com/macros/echo?user_content_key=-c8MG6WpUovCcccqVkg3xCUvkJZmgwhAnI-zerDDkR2rkbFec9ORsZjND5fGzD-YgOCi243L6jvCECiu4q4K9hRddLMpQXENOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa-4eloDPhUeiQn5_WMDQFrSv61u25tMbbSnSKpHyhLvd-svbAYBz-FaRjOkZu9ERgkquCQ_eIUDRCmE2GxhGnxWXYzZIjDWYbzEDEn8EiNmKWpVG1tix-wWMlCxrYU5CJJsmK3zk79fJHiUwXcipXAgi1LaDjl9Ua6uoBuHRaNtQtYZIDqe0NJU&lib=MRr6gq5QG-BfwetwxJMGUdU6GcXNAlbvE").readText()
            val database = getSharedPreferences("database", Context.MODE_PRIVATE)
            database.edit().apply {
                putString("key", json)
            }.apply()
        }
        val preferences= getSharedPreferences("database", Context.MODE_PRIVATE)
        val name= preferences.getString("key","nono")
        d("pablo","is $name")


            var doExist = true

            val products = Gson().fromJson(name, Array<Product>::class.java).toList()
            var noms = arrayListOf("")

            for (product in products) {
                noms.add(product.nom)
                d("pablo", product.id.toString())




            }



//Display del autocomplete i algoritme de recerca
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noms)
        BuscadorFase2.setAdapter(adapter)
            ConfirmarFase2.setOnClickListener {
                for (x in products) {
                    if (x.nom.equals(BuscadorFase2.text.toString(), true)) {
                        doExist = true
                        if(x.contenidor.equals("blau",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()

                            startActivity(Intent(this, blauresult::class.java))

                            break
                        }
                        if(x.contenidor.equals("verd",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()
                            startActivity(Intent(this, verdresult::class.java))
                            break
                        }
                        if(x.contenidor.equals("gris",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()
                            startActivity(Intent(this, grisresult::class.java))
                            break
                        }
                        if(x.contenidor.equals("groc",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()
                            startActivity(Intent(this, grocresult::class.java))
                            break
                        }
                        if(x.contenidor.equals("marro",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()
                            startActivity(Intent(this, marroresult::class.java))
                            break
                        }
                        if(x.contenidor.equals("punt verd",true)){
                            doExist = true
                            val nomEscollit=x.nom
                            val producteEscollit = getSharedPreferences("producteEscollit", Context.MODE_PRIVATE)
                            producteEscollit.edit().apply {
                                putString("result", nomEscollit)
                            }.apply()
                            startActivity(Intent(this, puntresult::class.java))
                            break
                        }

                    } else {
                        doExist = false
                    }
                }
                if (doExist == false) {
                    Toast.makeText(this,"Producte no trobat",
                        Toast.LENGTH_SHORT).show()

                }

            }

        fase2Back.setOnClickListener {
            finish()
        }


    }


}






