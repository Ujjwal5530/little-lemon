package com.malhotra.littlelemon.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuNetworkLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dao(): AppDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "Menu Database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
@Entity(tableName = "menu_items")
data class MenuNetworkLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "description")
    val description : String,
    @ColumnInfo(name = "price")
    val price : String,
    @ColumnInfo(name = "image")
    val image : String,
    @ColumnInfo(name = "category")
    val category : String
)

@Dao
interface AppDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items : List<MenuNetworkLocal>)
    @Query("SELECT * FROM menu_items")
    fun getMenuItems() : LiveData<List<MenuNetworkLocal>>
}