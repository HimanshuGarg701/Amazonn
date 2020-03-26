package com.example.amazonn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDao : ProductDAO

    companion object{
        // value of volatile variable will never be cached
        @Volatile
        private var INSATNCE : ProductDatabase? = null

        fun getInstance(context: Context) : ProductDatabase{
            synchronized(this){
                var instance = INSATNCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    ProductDatabase::class.java,
                                            "products_database")

                        .fallbackToDestructiveMigration()
                        .build()
                    INSATNCE = instance
                }
                return instance
            }
        }
    }
}