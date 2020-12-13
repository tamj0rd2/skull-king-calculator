package com.tamj0rd2.skullkingcalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.tamj0rd2.skullkingcalculator.components.PlayerInput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addAPlayer(view: View) {
        this.addPlayerRow()
        this.updatePlayerCount()
    }

    fun removeAPlayer(view: View) {}

    private fun addPlayerRow() {
        val rootLayout = this.getRootLayout()

        val layoutParams = LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT)
        layoutParams.startToStart = rootLayout.id
        layoutParams.endToEnd = rootLayout.id
        layoutParams.bottomToBottom = rootLayout.id

        val playerInput = PlayerInput(this, null)
        playerInput.id = View.generateViewId()
        playerInput.layoutParams = layoutParams
        rootLayout.addView(playerInput)
    }

    private fun updatePlayerCount() {
        val playerCount = this.findViewById<TextView>(R.id.playerCount)
//        playerCount.text = this.playerRowIds.size.toString()
        playerCount.text = "1"
    }

    private fun getRootLayout(): ConstraintLayout {
        return this.findViewById(R.id.root)
    }
}
