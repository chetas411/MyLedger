package com.example.myledger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myledger.models.Stocks_Model
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.JsonObject
import org.w3c.dom.Text

class Stocks : AppCompatActivity() {
    var url : String = "https://myledgerbackend.herokuapp.com/investments?id="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)
        val auth = Firebase.auth.currentUser!!.uid
        url += auth
        fetchdata()

    }

    private fun fetchdata() {
        val queue = Volley.newRequestQueue(this)


// Request a string response from the provided URL.
        val JsonObject = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener {
                val jsonObjectaraay = it.getJSONArray("data")
                val stockarray = ArrayList<Stocks_Model>()
                for(i in 0 until jsonObjectaraay.length())
                {
                    val jsonObject = jsonObjectaraay.getJSONObject(i)
                    val stock = Stocks_Model(
                        jsonObject.getString("companyName")
                        ,jsonObject.getString("changesPercentage")
                        ,jsonObject.getString("price")
                        ,jsonObject.getString("amount")
                    ,jsonObject.getString("url")
                    )
                    stockarray.add(stock)
                }



                    var company_name: TextView = findViewById(R.id.c1)
                    var changesPercentage: TextView = findViewById(R.id.g1)
                    var price: TextView = findViewById(R.id.pr1)
                    var amt: TextView = findViewById(R.id.amt1)
                    var urls: TextView = findViewById(R.id.u1)
                    var obj = stockarray.get(0)
                    company_name.text = obj.company
                    changesPercentage.text = obj.percGain
                    price.text = obj.price
                    amt.text = obj.amount
                    urls.text= obj.urls


//
//                    var company_name: TextView = findViewById(R.id.c2)
//                    var changesPercentage: TextView = findViewById(R.id.g2)
//                    var price: TextView = findViewById(R.id.pr2)
//                    var amt: TextView = findViewById(R.id.amt2)
//                    var urls: TextView = findViewById(R.id.u2)
//                    var obj = stockarray.get(0)
//                    company_name.text = obj.company
//                    changesPercentage.text = obj.percGain
//                    price.text = obj.price
//                    amt.text = obj.amount
//                    urls.text= obj.urls
//
//                {
//                    var company_name: TextView = findViewById(R.id.c2)
//                    var changesPercentage: TextView = findViewById(R.id.g2)
//                    var price: TextView = findViewById(R.id.pr1)
//                    var amt: TextView = findViewById(R.id.amt1)
//                    var urls: TextView = findViewById(R.id.u1)
//                    var obj = stockarray.get(0)
//                    company_name.text = obj.company
//                    changesPercentage.text = obj.percGain
//                    price.text = obj.price
//                    amt.text = obj.amount
//                    urls.text= obj.urls
//                }
//                {
//                    var company_name: android.widget.TextView = findViewById(com.example.myledger.R.id.c1)
//                    var changesPercentage: android.widget.TextView = findViewById(com.example.myledger.R.id.g1)
//                    var price: android.widget.TextView = findViewById(com.example.myledger.R.id.pr1)
//                    var amt: android.widget.TextView = findViewById(com.example.myledger.R.id.amt1)
//                    var urls: android.widget.TextView = findViewById(com.example.myledger.R.id.u1)
//                    var obj = stockarray.get(0)
//                    company_name.text = obj.company
//                    changesPercentage.text = obj.percGain
//                    price.text = obj.price
//                    amt.text = obj.amount
//                    urls.text= obj.urls
//                }
//                {
//                    var company_name: android.widget.TextView = findViewById(com.example.myledger.R.id.c1)
//                    var changesPercentage: android.widget.TextView = findViewById(com.example.myledger.R.id.g1)
//                    var price: android.widget.TextView = findViewById(com.example.myledger.R.id.pr1)
//                    var amt: android.widget.TextView = findViewById(com.example.myledger.R.id.amt1)
//                    var urls: android.widget.TextView = findViewById(com.example.myledger.R.id.u1)
//                    var obj = stockarray.get(0)
//                    company_name.text = obj.company
//                    changesPercentage.text = obj.percGain
//                    price.text = obj.price
//                    amt.text = obj.amount
//                    urls.text= obj.urls
//                }
//





            },
            {
                Toast.makeText(this,"(${it})", Toast.LENGTH_LONG).show()
            })
        queue.add(JsonObject)
    }
}