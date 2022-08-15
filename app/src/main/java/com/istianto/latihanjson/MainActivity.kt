package com.istianto.latihanjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.istianto.latihanjson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Lembaga Amil Zakat"
        }

        val data = parsingJSON()
        val mAdapter = LazAdapter()
        binding.rvLaz.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter.setData(data)
        }
    }

    private fun parsingJSON(): ArrayList<Laz> {
        val json = getJSONFromAsset()

        val getRootJSON = JSONObject(json.toString())

        val arrayDaftarLaz = getRootJSON.getJSONArray("lembaga_zakat")

        val arrayLaz = ArrayList<Laz>()

        for (i in 0 until arrayDaftarLaz.length()) {
            val getLaz = arrayDaftarLaz.getJSONObject(i)

            val Laz = Laz(
                organization_name = getLaz.getString("organization_name"),
                scope = getLaz.getString(" scope"),
                headquarter = getLaz.getString(" headquarter"),
            )
            arrayLaz.add(Laz)
        }
        return arrayLaz
    }

    private fun getJSONFromAsset(): String? {
        val json: String

        try {
            val stream =
                assets.open("lembaga_zakat.json")
            val size = stream.available()
            val buffer = ByteArray(size)

            stream.read(buffer)
            stream.close()

            json = String(buffer, Charsets.UTF_8)
        } catch (e: JSONException) {
            Log.e("JSON", "getJSONFromAsset: $e")
            return null
        }
        return json
    }
}