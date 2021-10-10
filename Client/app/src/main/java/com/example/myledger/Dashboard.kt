package com.example.myledger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myledger.Dao.userdao
import com.example.myledger.inputscreen.Expense_Details
import com.example.myledger.models.ExpenseDetails
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val auth = Firebase.auth.currentUser!!.uid
        val queue = Volley.newRequestQueue(this)
        val url = "https://myledgerbackend.herokuapp.com/user?id="+auth
        var currbal :String ="121"
        var new_balance : TextView = findViewById(R.id.show_bal)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    currbal = response.getString("currentbal")
                   new_balance.text=currbal

                },
                { error ->
                    Toast.makeText(this, "$currbal + dsnjvls", Toast.LENGTH_SHORT).show()
                }
        )
        queue.add(jsonObjectRequest)



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val mainactivityintent = Intent(this, MainActivity::class.java)
            startActivity(mainactivityintent)
            finish()
        }
        findViewById<Button>(R.id.invest).setOnClickListener { view ->
            val mainactivityintent = Intent(this, Stocks::class.java)
            startActivity(mainactivityintent)
            finish()
        }
    }
}