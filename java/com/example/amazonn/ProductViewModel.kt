package com.example.amazonn

import androidx.lifecycle.ViewModel


class ProductViewModel : ViewModel() {

    fun generateList(){
        var products =  mutableListOf<Product>(
            Product(0, "Bat", 10, "Used to play cricket"),
            Product(1, "Ball", 10, "Used to play cricket"),
            Product(2, "Bottle", 10, "Steel bottle to store hot water"),
            Product(3, "Iphone X", 10, "Quality phone from apple"),
            Product(4, "Oneplus 7", 10, "Quality phone from OnePlus"),
            Product(5, "Candle", 10, "Candle for dim light mode"),
            Product(6, "Bag", 10, "College bag to hold laptop and books"),
            Product(7, "Calculator", 10, "Your Math homework helper"),
            Product(8, "Television", 10, "Watch all movies and shows on big screen"),
            Product(9, "Leather Jacket", 10, "Look cool with the all new leather jacket collection")
        )
    }
}