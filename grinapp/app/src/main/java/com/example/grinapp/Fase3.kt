package com.example.grinapp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fase_3_layout.*
import java.io.*

const val EXTRA_MAP_TITLE="EXTRA_MAP_TITLE"
const val EXTRA_USER_MAP="EXTRA_USER_MAP"
private const val REQUEST_CODE=1234
private const val FILE_NAME="UserMaps.data"
class Fase3: AppCompatActivity() {
    private lateinit var userMaps: MutableList<UserMap>
    private lateinit var mapAdapter: MapsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fase_3_layout)
        supportActionBar?.title = "Crea el teu mapa"


        userMaps = deserializeUserMaps(this).toMutableList()

        //layout manager
        rvMaps.layoutManager = LinearLayoutManager(this)
        mapAdapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                Log.d("jefe", "onItemClick $position")
                val intent = Intent(this@Fase3, DisplayMapActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }


        })


        rvMaps.adapter = mapAdapter
        fabCreateMap.setOnClickListener {
            Log.d("mapache", "tap on fab")
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map, null)
        val dialog =
            AlertDialog.Builder(this)
                .setTitle("Títol del mapa")
                .setView(mapFormView)
                .setNegativeButton("Cancel.lar", null)
                .setPositiveButton("OK", null)
                .show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = mapFormView.findViewById<EditText>(R.id.etTitle).text.toString()

            if (title.trim().isEmpty()) {
                Toast.makeText(
                    this, "El títol no pot estar buit",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val intent = Intent(this@Fase3, CreateMapActivity::class.java)
            intent.putExtra(EXTRA_MAP_TITLE, title)
            startActivityForResult(intent, REQUEST_CODE)
            dialog.dismiss()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //pillar info de data object
            val userMap = data?.getSerializableExtra(EXTRA_USER_MAP) as UserMap
            Log.d("maquina", "onActivityResult ${userMap.title}")
            userMaps.add(userMap)
            mapAdapter.notifyItemInserted(userMaps.size - 1)
            serializeUserMaps(this, userMaps)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun serializeUserMaps(context: Context, userMaps: List<UserMap>) {
        Log.d("lol", "serialaze usermaps")
        ObjectOutputStream(FileOutputStream(getDataFile(context))).use { it.writeObject(userMaps) }
    }

    private fun deserializeUserMaps(context: Context): List<UserMap> {
        Log.d("lol", "DEserialaze usermaps")
        val dataFile = getDataFile(context)
        if (!dataFile.exists()) {
            Log.d("lol", "file doesn't exist")
            return emptyList()
        }
        ObjectInputStream(FileInputStream(dataFile)).use { return it.readObject() as List<UserMap> }
    }

    private fun getDataFile(context: Context): File {
        Log.d("lol", "Getting file from director ${context.filesDir}")
        return File(context.filesDir, FILE_NAME)
    }
}
