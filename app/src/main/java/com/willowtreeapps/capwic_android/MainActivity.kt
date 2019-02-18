package com.willowtreeapps.capwic_android

import android.app.Activity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : Activity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.database = FirebaseDatabase.getInstance().reference

        sendToRealTimeDatabase()
    }

    private fun sendToRealTimeDatabase() {
        val membersList = Arrays.asList(
            TeamMember("Daniel Willowtree", Date()),
            TeamMember("duke dog", Date())
        )

        val teamTree = Team("teamtree", membersList)

        this.database.child("capwic-jmu-2019")
            .child("teamdata")
            .child(teamTree.teamName)
            .setValue(teamTree)
    }
}
