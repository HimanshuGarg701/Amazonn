package com.example.amazonn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.lang.Exception


class ProductViewModel(private val productDao : ProductDAO, application: Application) : AndroidViewModel(application) {

    lateinit var products : ArrayList<Product>
    private val viewModelJob = Job()


    private val uiScope = CoroutineScope(IO + viewModelJob)

    //var product = MutableLiveData<Product>()

    var allProducts : List<Product>

    init{
        Log.d("TheErrorOfDao", "ENTERING?")
        generateList()

        initializeDatabase(products)
        allProducts = products
        launchProducts()
        viewModelJob.cancel()

    }

    private fun launchProducts(){
        try {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    allProducts = productDao.getAllProducts()
                    Log.d("TheErrorOfDao", allProducts.toString())
                }
            }
        }catch(e : Exception){
            Log.d("TheErrorOfDao", "${e.message}")
        }
    }


    private fun initializeDatabase(products : ArrayList<Product>){
        uiScope.launch {
            Log.d("TheErrorOfDao", "Entering Loop")
            for(product in products){
                insert(product)
               Log.d("TheErrorOfDao", product.toString())
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
            Product(0, "Bat", 10, 100.0, "Used to play cricket",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT9MoU4Vxz5C1pg-s5LxEumlAmXVlIoDb_XUj8N-fkPXPTajXmt&usqp=CAU"),
                Product(1, "Ball", 10, 10.5,"Used to play cricket",
            "https://www.istockphoto.com/photo/close-up-of-a-cricket-ball-gm493759086-77077225"),
            Product(2, "Bottle", 10, 25.0,"Steel bottle to store hot water",
            "https://media1.s-nbcnews.com/i/newscms/2019_27/1456290/screen_shot_2019-07-05_at_12-09-39_pm_3279718479aabc2067270235b6b037bc.png"),
            Product(3, "Iphone X", 10, 1000.0,"Quality phone from apple",
            "https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-x-new-1.jpg"),
            Product(4, "Oneplus 7", 10,400.0, "Quality phone from OnePlus",
            "https://cnet3.cbsistatic.com/img/7QY3TfjQI8AybNQu6TrRYkGVHZk=/868x488/2019/05/28/95381786-7bbe-44b4-8fbb-762231ef6e98/oneplus-7-review-5.jpg"),
            Product(5, "Candle", 10, 15.0,"Candle for dim light mode",
            "https://images.unsplash.com/photo-1528351655744-27cc30462816?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"),
            Product(6, "Bag", 10, 240.0,"College bag to hold laptop and books",
            "https://cdn.shopify.com/s/files/1/0277/0693/files/Website_Pack_BagsCollection_Block_Spring20Launch_031919_200x@2x.progressive.jpg?v=1584589900"),
            Product(7, "Calculator", 10,55.5, "Your Math homework helper",
            "https://images-na.ssl-images-amazon.com/images/I/7106ob3ATYL._AC_SX466_.jpg"),
            Product(8, "Television", 10, 1005.0,"Watch all movies and shows on big screen",
            "https://cnet1.cbsistatic.com/img/iE61mCAx70d5w9VH0TLfTr_Oi2M=/756x425/2019/06/07/38f10a70-91cf-422c-a485-af9c1380ff96/vizio-m-series-quantum01.jpg"),
            Product(9, "Leather Jacket", 10, 105.0,"Look cool with the all new leather jacket collection",
            "https://ae01.alicdn.com/kf/H4eb29dff80f54a99af67732e5e32c22cf/Men-s-Real-Leather-Jacket-Men-Motorcycle-Removable-Hood-winter-coat-Men-Warm-Genuine-Leather-Jackets.jpg_640x640q70.jpg")
        )
        Log.d("ViewModelEntered", "Generated List")
    }
}