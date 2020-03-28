package com.example.amazonn

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProductDAO {

    @Insert
    suspend fun insert(product : Product)

    @Update
    suspend fun update(product : Product)

    @Delete
    suspend fun delete(product : Product)

    @Query("SELECT * FROM product_data WHERE productId = :key")
    suspend fun getProduct(key : Long) : Product

    @Query("SELECT * FROM product_data ORDER BY productId")
    fun getAllProducts() : List<Product>

    @Query("DELETE FROM product_data")
    suspend fun deleteAll()
}