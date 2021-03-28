package com.example.rodem.a0Common.a0Object

import com.google.firebase.firestore.FirebaseFirestore


object GlobalFirebaseObject {

    val fireStore = FirebaseFirestore.getInstance()


    const val fbMeetingPosting = "meetingPosting"



    fun colMeetingPosting() = fireStore.collection(fbMeetingPosting)
}