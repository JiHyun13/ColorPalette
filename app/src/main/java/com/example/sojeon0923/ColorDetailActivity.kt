package com.example.sojeon0923

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorDetailActivity : AppCompatActivity() {

    private val db : ColorDatabase by lazy { ColorDatabase.getInstance(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_detail)

        val color = intent.getSerializableExtra("color") as Color //컬러에 보면 Serializeable 구현되어있음 객체 구현 뒤 받아오는 코드
        findViewById<View>(R.id.colorDetailView).setBackgroundColor(android.graphics.Color.parseColor(color.hex))
        findViewById<TextView>(R.id.txtHex).text = color.hex
        findViewById<TextView>(R.id.txtColorName).text = color.name //간단하게 뷰에 데이터 뿌려주는 코드

        findViewById<Button>(R.id.btnRemove).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.colorDao().delete(color)
                finish()
            }
        }
    }
}