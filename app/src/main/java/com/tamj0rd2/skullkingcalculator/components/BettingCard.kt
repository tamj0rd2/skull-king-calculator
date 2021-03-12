package com.tamj0rd2.skullkingcalculator.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.tamj0rd2.skullkingcalculator.R
import com.tamj0rd2.skullkingcalculator.viewmodels.Player
import kotlin.math.roundToInt


class BettingCard(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val playerNameLabel: TextView
    private val betLabel: TextView

    private var bet: Int = 0

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.betting_card, this, true)

        this.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, this.getHeightInDp(50))
        this.playerNameLabel = this.findViewById(R.id.playerNameLabel)
        this.betLabel = this.findViewById(R.id.betAmount)
    }

    fun setInfo(player: Player, bet: Int = 0) {
        this.bet = bet
        this.playerNameLabel.text = player.name
        this.betLabel.text = this.bet.toString()
    }

    fun incrementBet() {
        this.bet += 1
        this.betLabel.text = this.bet.toString()
    }

    fun decrementBet() {
        this.bet -= 1
        this.betLabel.text = this.bet.toString()
    }

    private fun getHeightInDp(height: Int): Int {
        val density = this.context?.resources?.displayMetrics?.density ?: throw Error("No density for some reason")
        return (height * density).roundToInt()
    }
}
