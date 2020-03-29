package com.example.amazonn

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory(private val productDao : ProductDAO,
                              private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(productDao, application) as T
        }
        throw IllegalArgumentException("Cannot Find ViewModel")
    }
}