package com.tamj0rd2.skullkingcalculator.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.tamj0rd2.skullkingcalculator.R
import com.tamj0rd2.skullkingcalculator.viewmodels.Player

class PlayerInput(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val textView: TextView
    private val editText: EditText

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.player_input, this, true)

        this.textView = findViewById(R.id.playerNameLabel)
        this.editText = findViewById(R.id.playerNameInput)
    }

    constructor(context: Context?, attrs: AttributeSet?, player: Player) : this(context, attrs) {
        this.textView.text = String.format(this.resources.getString(R.string.playerLabel), player.id)
        this.editText.setText(player.name)
    }

    fun getInputValue(): String {
        return this.editText.text.toString()
    }
}
