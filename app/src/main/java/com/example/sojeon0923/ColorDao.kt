package com.example.sojeon0923

import androidx.room.*

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors")
    fun getAll() : Array<Color>
    @Insert
    fun insert(vararg color : Color) //컬러 객체를 하나만 넣어도 되고, 여러개를 콤마로 넣어도 된다는 소리
    @Update
    fun update(color : Color)
    @Delete
    fun delete(color : Color) //생성, 읽기, 갱신, 삭제 CIUD 라고 부름
}