package com.example.amazonn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ProductsListActivity : AppCompatActivity() {

    private lateinit var viewModel : ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_products_list)

        val application = requireNotNull(this).application

        val productDao = ProductDatabase.getInstance(application).productDao

        val viewModelFactory = ProductViewModelFactory(productDao, application)

        val viewModelProduct = ViewModelProviders.of(this, viewModelFactory)
                                                    .get(ProductViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_cart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cart ->{
                val intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
            }
        }
        return onOptionsItemSelected(item)
    }
}
