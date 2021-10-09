package com.example.myledger.inputscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myledger.Dao.userdao
import com.example.myledger.MainActivity
import com.example.myledger.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Expense_Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense__details)

        var monthlyincome : EditText =findViewById(R.id.incomeedittext)
        var fixedexpense : EditText =findViewById(R.id.expensefixed)
        var setlimit_button : Button = findViewById(R.id.setlimitbutton)

        setlimit_button.setOnClickListener{
            var daily = (monthlyincome.text.toString().toInt() - fixedexpense.text.toString().toInt())/30;
            val user = Firebase.auth.currentUser
            val userdao = userdao()
            userdao.setdailylimit(user!!.uid,daily.toString())
            val mainactivityintent = Intent(this, MainActivity::class.java)
            Toast.makeText(this,"Limit Added",Toast.LENGTH_LONG).show()
            startActivity(mainactivityintent)
            finish()
        }


    }
}