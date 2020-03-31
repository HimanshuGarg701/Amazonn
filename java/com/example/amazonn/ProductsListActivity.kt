package com.example.amazonn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.amazonn.databinding.RecyclerViewProductsListBinding
import kotlinx.android.synthetic.main.recycler_view_products_list.*
import java.lang.Exception

@Suppress("DEPRECATION")
class ProductsListActivity : AppCompatActivity() {

    private lateinit var viewModelProduct : ProductViewModel
    private lateinit var binding : RecyclerViewProductsListBinding
    private lateinit var products : ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.recycler_view_products_list)
        Log.d("ProductListActivity", "Before Data Binding")
        binding = DataBindingUtil.setContentView(this, R.layout.recycler_view_products_list)

        Log.d("ProductListActivity", "After data binding")
        val application = requireNotNull(this).application
        Log.d("ProductListActivity", "After application created")

        val productDao = ProductDatabase.getInstance(application).productDao
        Log.d("ProductListActivity", "Created Product DAO Object")


        val viewModelFactory = ProductViewModelFactory(productDao, application)
        Log.d("ProductListActivity", "Before")
         viewModelProduct = ViewModelProviders.of(this, viewModelFactory).get(ProductViewModel::class.java)


        //viewModelProduct = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        Log.d("ProductListActivity", "After View Model Created")
//        viewModelProduct.allProducts.observe(this, Observer {
//            products = it as ArrayList<Product>
//        })
        products = viewModelProduct.allProducts as ArrayList<Product>
        //Log.d("ProductListActivity", "${products.toString()}")
        val adapter = ProductListAdapter(products)
        binding.recyclerProductList.adapter = adapter


        binding.addProduct.setOnClickListener {
            try{
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)}
            catch(e : Exception){
                Log.d("ErrorOfProduct", e.message)
            }
        }
        binding.productViewModel = viewModelProduct
        binding.lifecycleOwner = this
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
        return super.onOptionsItemSelected(item)
    }
}
