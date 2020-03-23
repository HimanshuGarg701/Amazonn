package com.example.amazonn

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProductDAO {

    @Insert
    fun insert(product : Product)

    @Update
    fun update(product : Product)

    @Query("SELECT * FROM product_data WHERE productId = :key")
    fun getProduct(key : Long) : Product

    @Query("SELECT * FROM product_data ORDER BY productId")
    fun getAllProducts()

    @Query("DELETE FROM product_data")
    fun deleteAll()
}