package com.example.sojeon0923

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "colors")
data class Color(
    @PrimaryKey(autoGenerate = true)
    val _id : Int = 0,
    @ColumnInfo(name = "hex_color")
    val hex : String,
    val name : String
) : Serializable //데이터 직렬화. 나중에 intent 에 객체 정보를 담아서 보내줄거라 사용함
