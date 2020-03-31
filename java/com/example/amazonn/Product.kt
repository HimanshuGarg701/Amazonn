package com.example.amazonn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_data")
data class Product(

    @PrimaryKey
    val productId : Long,

    @ColumnInfo(name = "product_name")
    val name: String,

    @ColumnInfo(name = "product_quantity")
    val quantity: Int,

    @ColumnInfo(name = "product_price")
    val price : Double,

    @ColumnInfo(name="product_description")
    val description : String,

    @ColumnInfo(name="image_url")
    val imageURL : String){
}