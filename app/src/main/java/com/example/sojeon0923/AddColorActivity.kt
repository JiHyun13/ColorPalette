package com.example.sojeon0923

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddColorActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private val redSeekBar : SeekBar by lazy { findViewById(R.id.redSeekBar) }
    private val greenSeekBar : SeekBar by lazy { findViewById(R.id.greenSeekBar) }
    private val blueSeekBar : SeekBar by lazy { findViewById(R.id.blueSeekBar) }


    private val txtColorHax : TextView by lazy { findViewById(R.id.txtColorHex) }
    private val colorView : View by lazy { findViewById(R.id.colorView) }

    private val db : ColorDatabase by lazy { ColorDatabase.getInstance(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)
        val editText = findViewById<EditText>(R.id.editTextColorTitle)
        redSeekBar.setOnSeekBarChangeListener(this)
        greenSeekBar.setOnSeekBarChangeListener(this)
        blueSeekBar.setOnSeekBarChangeListener(this) //콜백으로 참조를 넘겨줌

        findViewById<Button>(R.id.btnAddColor).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.colorDao().insert(Color(hex = txtColorHax.text.toString(),  //Color 데이터 클래스에는 id 값도 있는데 우리가 임의로 만들면 안됨. 따라서 id는 임의로 만들라고 하고 우리는 두개만 넣어주기
                    name = editText.text.toString()))
                finish()
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val newColor = android.graphics.Color.argb(255,redSeekBar.progress,greenSeekBar.progress,blueSeekBar.progress)
        colorView.setBackgroundColor(newColor)
        val haxColor = String.format("#%06X", (0xFFFFFF and newColor)) //처음은 16자리로 나타내겠다는거고 두번째는 and 연산자를 이용해 new color를 넣음
        txtColorHax.text = haxColor
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}