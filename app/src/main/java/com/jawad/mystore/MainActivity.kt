package com.jawad.mystore

import android.app.AlertDialog
import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        activity_main_btnLogin.setOnClickListener {


            val loginURL = "http://dreamlogix.freevar.com/user_login.php?email=" +
                    activity_main_edtEmail.text.toString() +
                    "&password=" + activity_main_edtPassword.text.toString()

            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginURL, Response.Listener
            { response ->


                if (response.equals("The user does exist")) {

                    Persons.email = activity_main_edtEmail.text.toString()
                    Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(this@MainActivity, HomeScreen::class.java)
                    startActivity(homeIntent)

                } else {

                    val dialogBuilder= AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()

                }

            }, Response.ErrorListener { error ->

                val dialogBuilder= AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()

            })

            requestQ.add(stringRequest)


        }


        activity_main_btnSignUp.setOnClickListener {

            var signUpIntent = Intent(this@MainActivity, SignUpLayout::class.java)
            startActivity(signUpIntent)

        }
    }
}