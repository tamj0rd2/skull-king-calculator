package com.tamj0rd2.skullkingcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.tamj0rd2.skullkingcalculator.components.PlayerInput
import com.tamj0rd2.skullkingcalculator.viewmodels.Player

class GameSetupFragment : Fragment(R.layout.fragment_game_setup) {
    companion object {
        const val FragmentTag = "gameSetupFragment"
    }

    private lateinit var playerCountTextView: TextView
    private lateinit var topLevelLayout: ConstraintLayout
    private lateinit var removePlayerButton: Button
    private lateinit var addPlayerButton: Button
    private lateinit var playerConfigBarrier: Barrier
    private lateinit var startGameButton: Button

    private val players = mutableListOf(Player(1), Player(2))
    private val playerInputs = mutableListOf<PlayerInput>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.activity?.title = this.resources.getString(R.string.gameSetupTitle)

        this.topLevelLayout = view.findViewById(R.id.topLevelLayout)
        this.playerConfigBarrier = view.findViewById(R.id.playerConfig)
        this.playerCountTextView = view.findViewById(R.id.playerCount)

        this.removePlayerButton = view.findViewById(R.id.removePlayer)
        this.removePlayerButton.setOnClickListener { this.removePlayerInput() }

        this.addPlayerButton = view.findViewById(R.id.addPlayer)
        this.addPlayerButton.setOnClickListener { this.addNewPlayer() }

        this.startGameButton = view.findViewById(R.id.startGame)
        this.startGameButton.setOnClickListener { this.startGame() }

        this.players.forEach { player -> this.addPlayerInput(player) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.players.forEachIndexed { index, player -> player.name = this.playerInputs[index].getInputValue() }
        this.playerInputs.clear()
    }

    private fun addNewPlayer() {
        val player = Player(this.players.size + 1)
        this.players.add(player)
        this.addPlayerInput(player)
    }

    private fun addPlayerInput(player: Player) {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.startToStart = this.topLevelLayout.id
        layoutParams.endToEnd = this.topLevelLayout.id
        layoutParams.topToBottom = this.playerInputs.lastOrNull()?.id ?: this.playerConfigBarrier.id

        val playerInput = PlayerInput(this.context, null, player)
        playerInput.id = View.generateViewId()
        playerInput.layoutParams = layoutParams

        playerInputs.add(playerInput)
        this.topLevelLayout.addView(playerInput)
        this.updateCountAndButtonStatuses()
    }

    private fun removePlayerInput() {
        val lastPlayerInput = this.playerInputs.removeAt(this.playerInputs.size - 1)
        this.topLevelLayout.removeView(lastPlayerInput)
        this.updateCountAndButtonStatuses()
    }

    private fun updateCountAndButtonStatuses() {
        this.removePlayerButton.isEnabled = this.playerInputs.size > 2
        this.addPlayerButton.isEnabled = this.playerInputs.size < 6
        this.playerCountTextView.text = this.playerInputs.size.toString()
    }

    private fun startGame() {
        val players = this.players
        this.parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            replace(R.id.fragment_container_view, DealAndBetFragment.newInstance(players, 1))
        }
    }
}
