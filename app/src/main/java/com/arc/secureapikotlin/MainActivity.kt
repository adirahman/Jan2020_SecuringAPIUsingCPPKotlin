package com.arc.secureapikotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun BASEURL(): String?

    companion object
    {
        // Used to load the 'native-lib' library on application startup.
        init
        {
            System.loadLibrary("native-lib")
        }
    }

    var fanAdapter: FanAdapter? = null
    var modelFans: ArrayList<ModelFan> = ArrayList()
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this).apply {
            title = "Mohon tunggu"
            setCancelable(false)
            setMessage("Sedang menampilkan data")
        }

        rv_fan.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        loadJSON()
    }

    private fun loadJSON() {
        progressDialog!!.show()
        AndroidNetworking.get(BASEURL())
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog!!.dismiss()
                        val jsonArray = response.getJSONArray("android")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val dataApi = ModelFan(
                                jsonObject.getString("name"),
                                jsonObject.getString("ver"),
                                jsonObject.getString("api")
                            )
                            modelFans.add(dataApi)
                        }
                        fanAdapter = FanAdapter(modelFans)
                        rv_fan.setAdapter(fanAdapter)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@MainActivity,
                            "Gagal menampilkan data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onError(anError: ANError) {
                    progressDialog!!.dismiss()
                    Toast.makeText(
                        this@MainActivity,
                        "Tidak ada jaringan internet!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
