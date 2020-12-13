package com.tamj0rd2.skullkingcalculator.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.tamj0rd2.skullkingcalculator.R

class PlayerInput(context: Context?, attrs: AttributeSet?, playerId: Int) : LinearLayout(context, attrs) {

    private val textView: TextView
    private val editText: EditText

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.player_input, this, true)

        this.textView = findViewById(R.id.playerNameLabel)
        this.textView.text = String.format(this.resources.getString(R.string.playerLabel), playerId)

        this.editText = findViewById(R.id.playerNameInput)
    }
}
