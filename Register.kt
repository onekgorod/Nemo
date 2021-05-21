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


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_login.setOnClickListener {

            startActivity(Intent(this@Register, Login::class.java))
        }

        button_register.setOnClickListener {
            when {
                TextUtils.isEmpty(name_register.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter your name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(age_register.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter your age.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(email_register.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password_register.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter a password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(phone_register.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter your phone number.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = email_register.text.toString().trim { it <= ' '}
                    val password: String = password_register.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@Register,
                                        "Your registration is successful",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val intent =
                                        Intent(this@Register, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else{
                                    Toast.makeText(
                                        this@Register,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

            })

        }

    }
}}}