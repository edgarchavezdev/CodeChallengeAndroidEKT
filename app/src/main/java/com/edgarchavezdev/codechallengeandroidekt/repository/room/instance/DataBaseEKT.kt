package com.edgarchavezdev.codechallengeandroidekt.repository.room.instance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edgarchavezdev.codechallengeandroidekt.repository.room.model.PokemonLocal
import com.edgarchavezdev.codechallengeandroidekt.repository.room.dao.PokemonDao

@Database(entities = [PokemonLocal::class], version = 2, exportSchema = false)
abstract class DataBaseEKT : RoomDatabase() {

    abstract fun pokemon() : PokemonDao

    companion object {

        @Synchronized
        fun getRoomInstance(context: Context): DataBaseEKT =
             Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseEKT::class.java,
                    "DataBaseEKT"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}