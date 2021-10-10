package com.example.myledger.Dao

import com.example.myledger.models.ExpenseDetails
import com.example.myledger.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class userdao() {
    val db  = FirebaseFirestore.getInstance()
    val usercollection = db.collection("users")
    fun addUser( user : User?) {

        user?.let {

                usercollection.document(user.userid).set(it)
            }
        }

    fun getUserById(uID: String) : Task<DocumentSnapshot> {
        return usercollection.document(uID).get()
    }
    fun getCurrBalById(uID: String) : String {
        return usercollection.document(uID).get().result["currentbal"].toString()

    }
//    fun add_transaction(userID : String, new_bal: ExpenseDetails()){
//        usercollection.document(userID).update("currentbal", new_bal)
//    }
    fun setdailylimit(userID : String, limit : String) {

            usercollection.document(userID).update("dailyexpenselimit", limit)
        usercollection.document(userID).update("currentbal", limit)
    }
    fun setcurrbal(userID : String, limit : String) {

        usercollection.document(userID).update("currentbal", limit)
    }

    fun addtransaction(userID : String, new_bal: ExpenseDetails) {

        var obj = new_bal.expenditure;
        var obj2 = new_bal.reason
        usercollection.document(userID).collection("expenseList").add(new_bal)

    }

}