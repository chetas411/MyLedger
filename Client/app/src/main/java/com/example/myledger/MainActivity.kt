package com.example.myledger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myledger.Dao.userdao
import com.example.myledger.inputscreen.Expense_Details
import com.example.myledger.models.ExpenseDetails
import com.example.myledger.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var radioGroup : RadioGroup = findViewById(R.id.radio_group)
        var add_transaction : Button = findViewById(R.id.button_add_tran)
        var trans_value : EditText = findViewById(R.id.editTextNumber)
        var message : EditText = findViewById(R.id.Message_reason)
        var radioButton : RadioButton
        add_transaction.setOnClickListener {
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(selectedOption)

            // Instantiate the RequestQueue.
            val auth = Firebase.auth.currentUser!!.uid
            val queue = Volley.newRequestQueue(this)
            val url = "https://myledgerbackend.herokuapp.com/user?id="+auth
            var currbal :String ="121"
            val Userdao=userdao()

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                    { response ->
                          currbal = response.getString("currentbal")
                           val list_trans = response.get("expenseList")
                        val final_change : String
                        if(radioButton.text.toString()=="Spent")
                            final_change = (currbal.toInt()- trans_value.text.toString().toInt()).toString()
                        else{
                            final_change = (currbal.toInt() + trans_value.text.toString().toInt()).toString()
                            Toast.makeText(this, "$currbal + dsnjvls", Toast.LENGTH_SHORT).show()
                        }
                        Userdao.setcurrbal(auth,final_change)

                        var expense_t = ExpenseDetails(message.text.toString(),trans_value.text.toString())
                        Userdao.addtransaction(auth,expense_t)
                    },
                    { error ->
                        Toast.makeText(this, "$currbal + dsnjvls", Toast.LENGTH_SHORT).show()
                    }
            )
            queue.add(jsonObjectRequest)


            val mainactivityintent = Intent(this, Dashboard::class.java)
            startActivity(mainactivityintent)
            finish()



        }

    }
}