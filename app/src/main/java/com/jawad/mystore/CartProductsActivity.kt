package com.jawad.mystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*
import java.util.ArrayList

class CartProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        var cartProductsUrl = "http://dreamlogix.freevar.com/order_temp.php?email=${Persons.email}"
        var cartProductsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductsUrl, null, Response.Listener{
                response ->


            for (joIndex in 0.until(response.length())) { // id, name, price, email, amount

                cartProductsList.add("${response.getJSONObject(joIndex).getInt("id")}" +
                        " \n ${response.getJSONObject(joIndex).getString("name")}" +
                        " \n ${response.getJSONObject(joIndex).getInt("price")}" +
                        " \n ${response.getJSONObject(joIndex).getString("email")}" +
                        " \n ${response.getJSONObject(joIndex).getInt("amount")}")

            }

            var cartProductsAdapter = ArrayAdapter(this@CartProductsActivity, android.R.layout.simple_list_item_1, cartProductsList)
            cartProductsListView.adapter = cartProductsAdapter

        }, Response.ErrorListener { error ->



        })


        requestQ.add(jsonAR)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.continueShoppingItem) {

            var intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)

        } else if (item?.itemId == R.id.declineOrderItem) {

            var deleteUrl = "http://dreamlogix.freevar.com/decline_order.php?email=${Persons.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            var stringRequest = StringRequest(Request.Method.GET, deleteUrl, Response.Listener{
                    response ->


                var intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)

            }, Response.ErrorListener {
                    error ->



            })

            requestQ.add(stringRequest)
        } else if (item?.itemId == R.id.verifyOrderItem) {


            var verifyOrderUrl = "http://dreamlogix.freevar.com/confirm_order.php?email=${Persons.email}"; ///temporary
            var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            var stringRequest = StringRequest(Request.Method.GET, verifyOrderUrl, Response.Listener {
                    response ->


                var intent = Intent(this, FinalizeShoppingActivity::class.java)
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                intent.putExtra("LATEST_INVOICE_NUMBER", response)
                startActivity(intent)


            }, Response.ErrorListener { error ->  })



            requestQ.add(stringRequest)
        }

        return super.onOptionsItemSelected(item)
    }

}
