package com.willowtreeapps.capwic_android

import android.app.Activity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : Activity() {

    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.firestore = FirebaseFirestore.getInstance()

        sendHelloWorldToFirestore()
    }

    private fun sendHelloWorldToFirestore() {
        this.firestore
            .collection("eventdata")
            .document("1337")
            .set(TestData("Hello firebase i am an app"))
    }
}
