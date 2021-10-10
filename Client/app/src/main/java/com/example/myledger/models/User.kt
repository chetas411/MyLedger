package com.example.myledger.models

import com.example.myledger.models.ExpenseDetails

class User(val userid : String = "",
           val  displayname :String ="",
           val imageurl :String="",
           val dailyexpenselimit : String ="0",
           val currentbal:String ="0",
           val expenseList : List<ExpenseDetails>  ) {
}