package com.example.nemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        buttonregister.setOnClickListener {

            startActivity(Intent(this@Login, Register::class.java))
        }
        buttonlogin.setOnClickListener {
            when {

                TextUtils.isEmpty(email_login.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(Password_login.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter a password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = email_login.text.toString().trim { it <= ' '}
                    val password: String = Password_login.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    Toast.makeText(
                                        this@Login,
                                        "You are logged in.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@Login, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else{
                                    Toast.makeText(
                                        this@Login,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

    }
}}}}}