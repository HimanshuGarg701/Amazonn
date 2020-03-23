package com.example.amazonn

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProductDAO {

    @Insert
    fun insert(product : Product)

    @Update
    fun update(product : Product)

    @Delete
    fun delete(product : Product)

    @Query("SELECT * FROM product_data WHERE productId = :key")
    fun getProduct(key : Long) : Product

    @Query("SELECT * FROM product_data ORDER BY productId")
    fun getAllProducts() : LiveData<List<Product>>

    @Query("DELETE FROM product_data")
    fun deleteAll()
}