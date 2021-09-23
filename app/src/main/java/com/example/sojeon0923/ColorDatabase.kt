package com.example.sojeon0923

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Color::class], version = 1)
abstract class ColorDatabase : RoomDatabase() { //처음 만들면 빨간 줄이 뜬다. 여기서 내가 스스로 임플리먼트 하면 내가 db를 만드는거고 나는 room db가 해줄거니까 추상화 하면됨
    abstract fun colorDao() : ColorDao
    companion object{
        @Volatile
        private var INSTANCE : ColorDatabase? = null
        fun getInstance(context : Context) : ColorDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ColorDatabase::class.java, "color_database"
                ).fallbackToDestructiveMigration() //버전이 올라갔을 때 얘 없으면 오류남
                    .build()
                    .also { INSTANCE = it }

            }
        }
    }
}