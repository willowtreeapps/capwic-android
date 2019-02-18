package com.willowtreeapps.capwic_android

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*
import java.util.*

class MainActivity : Activity() {

    private lateinit var database: DatabaseReference
    private lateinit var teamsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.database = FirebaseDatabase.getInstance().reference

        sendToRealTimeDatabase()
        readRealTimeDatabase()
    }

    private fun sendToRealTimeDatabase() {
        val membersList = Arrays.asList(
            TeamMember("Daniel Willowtree", Date()),
            TeamMember("duke dog", Date())
        )

        val teamTree = Team("teamtree", membersList)
        val team3 = Team("team3", membersList)

        this.database.child("capwic-jmu-2019")
            .child("teamdata")
            .child(teamTree.teamName)
            .setValue(teamTree)

        this.database.child("capwic-jmu-2019")
            .child("teamdata")
            .child(team3.teamName)
            .setValue(team3)
    }

    private fun readRealTimeDatabase() {
        this.teamsTextView = findViewById(R.id.teams_text_view)

        val team3Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val team = dataSnapshot.getValue(Team::class.java)

                teamsTextView.text = team?.teamName
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        }

        val team3Reference = this.database.child("capwic-jmu-2019")
            .child("teamdata")
            .child("team3")

        team3Reference.addValueEventListener(team3Listener)
    }
}
