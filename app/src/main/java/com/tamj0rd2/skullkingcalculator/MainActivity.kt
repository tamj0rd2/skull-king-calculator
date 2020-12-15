package com.tamj0rd2.skullkingcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var gameSetupFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            this.supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<GameSetupFragment>(R.id.fragment_container_view, GameSetupFragment.FragmentTag)
            }
            return
        }

        gameSetupFragment =
            this.supportFragmentManager.getFragment(savedInstanceState, "gameSetupFragment")
                ?: throw Error("No fragment found...")
    }
}
