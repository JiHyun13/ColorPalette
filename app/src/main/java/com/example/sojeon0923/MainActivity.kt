package com.example.sojeon0923

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val db : ColorDatabase by lazy { ColorDatabase.getInstance(applicationContext) } //늦은 초기화, 쓰일 때 처음 초기화된다
    private val recycler : RecyclerView by lazy { findViewById(R.id.colorRecycler) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{
            startActivity(Intent(this, AddColorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        refreshUI()
    }

    private fun refreshUI(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.colorDao().getAll().toMutableList()
            db.colorDao().getAll().toMutableList()
            withContext(Dispatchers.Main){
                if(recycler.adapter == null)
                    recycler.adapter = ColorAdapter(data)
                else
                    (recycler.adapter as ColorAdapter).changeData(data) // 기존 어뎁터가 있으니까 그걸 캐스팅 해주고 데이터를 바꿈
            }
        }
    }
}