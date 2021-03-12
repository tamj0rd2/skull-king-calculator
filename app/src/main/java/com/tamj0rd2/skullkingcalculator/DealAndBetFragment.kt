package com.tamj0rd2.skullkingcalculator

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tamj0rd2.skullkingcalculator.components.BettingCard
import com.tamj0rd2.skullkingcalculator.viewmodels.Player

private const val ARG_PLAYERS = "players"
private const val ARG_ROUND_NUMBER = "roundNumber"

class DealAndBetFragment : Fragment(R.layout.fragment_deal_and_bet) {
    private lateinit var players: MutableList<Player>
    private var roundNumber: Int = 0

    private val bettingCards = mutableListOf<BettingCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.players =
                it.getParcelableArrayList<Player>(ARG_PLAYERS)?.toMutableList() ?: mutableListOf()
            this.roundNumber = it.getInt(ARG_ROUND_NUMBER)
        }
        this.activity?.title =
            String.format(this.resources.getString(R.string.dealAndBetTitle), this.roundNumber)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val instructionsTextView = view.findViewById<TextView>(R.id.instructions)
        instructionsTextView.text =
            String.format(this.resources.getString(R.string.dealCardsAndBet), this.roundNumber)

        val topLevelLayout = view.findViewById<LinearLayout>(R.id.topLevelLayout)
        this.players.forEach { player -> this.addBettingCard(topLevelLayout, player) }
    }

    companion object {
        @JvmStatic
        fun newInstance(players: MutableList<Player>, roundNumber: Int) =
            DealAndBetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PLAYERS, ArrayList(players))
                    putInt(ARG_ROUND_NUMBER, roundNumber)
                }
            }
    }

    private fun startGame() {
        val bets = this.players.mapIndexed { index, player ->  }
    }

    private fun addBettingCard(linearLayout: LinearLayout, player: Player) {
        val bettingCard = BettingCard(this.context, null)
        bettingCard.setInfo(player, 2)
        linearLayout.addView(bettingCard)
        this.bettingCards.add(bettingCard)
    }
}

data class PlayerBet(val player: Player, val bet: ) {
    var wins = 0

    fun incrementWins() {
        this.wins ++
    }
}
