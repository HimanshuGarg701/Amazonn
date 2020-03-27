package com.example.amazonn

import android.content.Context
import android.util.Log
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
            Log.d("Inside GetInstance", "Database entry")
            synchronized(this){
                var instance = INSATNCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    ProductDatabase::class.java,
                                            "product_data")

                        .fallbackToDestructiveMigration()
                        .build()
                    INSATNCE = instance
                }
                Log.d("Inside GetInstance", "Database exit")
                return instance
            }
        }
    }
}

