package com.example.myledger

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myledger.Dao.userdao
import com.example.myledger.inputscreen.Expense_Details
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignGoogle : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 123
    private val TAG = "SignInActivity Tag"
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_google)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        var signInButton : SignInButton = findViewById(R.id.signinbutton)
        signInButton.setOnClickListener {
            signIn()
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase

                val account  = task.getResult(ApiException::class.java)!!
                // Log.d( "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
                 Toast.makeText(this,"Google sign in success", Toast.LENGTH_LONG).show()
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Google sign in failed", e)
                Toast.makeText(this,"Google sign in failed ${e.toString()}", Toast.LENGTH_LONG).show()
                // ...
            }
        }
    }

    private fun handlesigninresult(task: Task<GoogleSignInAccount>?) {

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val auth = auth.signInWithCredential(credential).addOnCompleteListener(this){
            if(it.isSuccessful)
            {
                val firebaseUser = auth.currentUser
                UpdateUI(firebaseUser)
            }
        }


        val signinbutton : SignInButton = findViewById((R.id.signinbutton))
        signinbutton.visibility = View.INVISIBLE


    }



    private fun UpdateUI(firebaseUser: FirebaseUser?) {
        if(firebaseUser!=null)
        {

            val user = User(firebaseUser.uid,firebaseUser.displayName.toString(),firebaseUser.photoUrl.toString(),"")
            val dao = userdao()
            Toast.makeText(this,"done",Toast.LENGTH_LONG).show()
            dao.addUser(user)
            Toast.makeText(this,"User Added",Toast.LENGTH_LONG).show()
            val mainactivityintent = Intent(this,Expense_Details::class.java)
            startActivity(mainactivityintent)
            finish()
        }else{

            val signinbutton : SignInButton = findViewById((R.id.signinbutton))
            signinbutton.visibility = View.VISIBLE

        }
    }
}