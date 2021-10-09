package com.example.myledger.Dao

import android.provider.Settings
import com.example.myledger.User
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
    fun setdailylimit(userID : String, limit : String) {

            usercollection.document(userID).update("dailyexpense", limit)


    }



}