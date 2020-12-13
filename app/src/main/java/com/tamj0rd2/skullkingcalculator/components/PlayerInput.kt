package com.tamj0rd2.skullkingcalculator.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.tamj0rd2.skullkingcalculator.R

class PlayerInput(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var textView: TextView
    private var editText: EditText

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.player_input, this, true)

        this.textView = findViewById(R.id.playerNameLabel)
        this.editText = findViewById(R.id.playerNameInput)
    }
}
