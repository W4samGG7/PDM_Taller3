package com.pdmcourse2026.basictemplate.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdmcourse2026.basictemplate.data.database.dao.OptionDao
import com.pdmcourse2026.basictemplate.data.database.dao.QuestionDao
import com.pdmcourse2026.basictemplate.data.database.entities.OptionEntity
import com.pdmcourse2026.basictemplate.data.database.entities.QuestionEntity

@Database(
    entities = [QuestionEntity::class, OptionEntity::class],
    version = 2,
    exportSchema = false
)
abstract  class AppDataBase : RoomDatabase(){

    abstract fun optionDao(): OptionDao
    abstract fun questionDao(): QuestionDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDataBase::class.java,
                    name = "rankeuca_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}