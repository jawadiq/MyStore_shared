package com.jawad.mystore

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AmountFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var fragmentView =  inflater.inflate(R.layout.fragment_amount, container, false)


        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddToCart = fragmentView.findViewById<ImageButton>(R.id.btnAddToCart)

        btnAddToCart.setOnClickListener {


            var ptoUrl = "http://dreamlogix.freevar.com/temporary_orders.php?email=${Persons.email}&product_id=${Persons.addToCartProductID}&amount=${edtEnterAmount.text.toString()}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET, ptoUrl, Response.Listener{ response ->

                var intent = Intent(activity, CartProductsActivity::class.java)

                startActivity(intent)


            }, Response.ErrorListener { error ->


            })

            requestQ.add(stringRequest)

        }

        return fragmentView

    }


}