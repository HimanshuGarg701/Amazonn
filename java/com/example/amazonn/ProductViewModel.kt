package com.example.amazonn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class ProductViewModel(private val productDao : ProductDAO, application: Application) : AndroidViewModel(application) {

    lateinit var products : ArrayList<Product>
    private val viewModelJob = Job()


    private val uiScope = CoroutineScope(IO + viewModelJob)

    //var product = MutableLiveData<Product>()


    init{
        Log.d("EnteredViewModel", "ENTERING?")
        generateList()
        //initializeDatabase(products)
        //viewModelJob.complete()
    }
    //var allProducts = productDao.getAllProducts()

    private fun initializeDatabase(products : ArrayList<Product>){
        uiScope.launch {
            for(product in products){
                insert(product)
            }
            //insert(Product(12, "Shirt", 20, 15.0, "Funky zara"))
        }
    }

    private suspend fun insert(product : Product){
        Log.d("InsertProduct", "Added product to database")
        withContext(Dispatchers.IO){
            productDao.insert(product)
        }
    }

    private fun generateList(){
         products =  arrayListOf<Product>(
            Product(0, "Bat", 10, 100.0, "Used to play cricket"),
            Product(1, "Ball", 10, 10.5,"Used to play cricket"),
            Product(2, "Bottle", 10, 25.0,"Steel bottle to store hot water"),
            Product(3, "Iphone X", 10, 1000.0,"Quality phone from apple"),
            Product(4, "Oneplus 7", 10,400.0, "Quality phone from OnePlus"),
            Product(5, "Candle", 10, 15.0,"Candle for dim light mode"),
            Product(6, "Bag", 10, 240.0,"College bag to hold laptop and books"),
            Product(7, "Calculator", 10,55.5, "Your Math homework helper"),
            Product(8, "Television", 10, 1005.0,"Watch all movies and shows on big screen"),
            Product(9, "Leather Jacket", 10, 105.0,"Look cool with the all new leather jacket collection")
        )
        Log.d("ViewModelEntered", "Generated List")
    }
}