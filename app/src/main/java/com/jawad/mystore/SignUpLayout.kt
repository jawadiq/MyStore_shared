package com.jawad.mystore

import android.app.AlertDialog
import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.sign_up_layout.*

class SignUpLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_layout)

        sign_up_layout_btnSignUp.setOnClickListener {

            if (sign_up_layout_edtPassword.text.toString().equals(
                    sign_up_layout_edtConfirmPassword.text.toString())) {

                // Registration Process

                val signUpURL = "http://dreamlogix.freevar.com/user_registration.php?email=" +
                        sign_up_layout_edtEmail.text.toString() +
                        "&username=" +
                        sign_up_layout_edtUsername.text.toString() +
                        "&password=" + sign_up_layout_edtPassword.text.toString()
                val requestQ = Volley.newRequestQueue(this@SignUpLayout)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener
                { response ->

                    if (response.equals("A user with this Email Address already exists")) {

                        val dialogBuilder= AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()


                    } else {

//                        val dialogBuilder= AlertDialog.Builder(this)
//                        dialogBuilder.setTitle("Message")
//                        dialogBuilder.setMessage(response)
//                        dialogBuilder.create().show()

                        Persons.email = sign_up_layout_edtEmail.text.toString()

                        Toast.makeText(this@SignUpLayout, response, Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this@SignUpLayout, HomeScreen::class.java)
                        startActivity(homeIntent)

                    }

                }, Response.ErrorListener { error ->

                    val dialogBuilder= AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                })

                requestQ.add(stringRequest)

            } else {

                val dialogBuilder= AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()

            }


        }

        sign_up_layout_btnLogin.setOnClickListener {

            finish()

        }

    }
}
