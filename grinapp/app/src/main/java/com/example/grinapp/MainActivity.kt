package com.example.grinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fase_2_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title="Benvingut a Grin"
        d("pablo","hi my bro")



        ToFase2.setOnClickListener {
            startActivity(Intent(this, Fase2::class.java))
        }
        toHelp.setOnClickListener {
            startActivity(Intent(this, Help::class.java))
        }
        toAboutUs.setOnClickListener {
           startActivity(Intent(this, AboutUs::class.java))
        }
        toFase3.setOnClickListener {
            startActivity(Intent(this, Fase3::class.java))
        }







        }



    }











