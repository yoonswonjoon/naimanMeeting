package com.example.rodem.a0Common.a0Object

import com.google.firebase.firestore.FirebaseFirestore


object GlobalFirebaseObject {

    val fireStore = FirebaseFirestore.getInstance()



    const val fbPosting             = "posting"
    const val fbMeetingPosting      = "meetingPosting"
    const val fbStudentPosting      = "studentPosting"
    const val fbWorkerPosting       = "workerPosting"


    fun colPosting() = fireStore.collection(fbPosting)
    fun colMeetingPosting() = fireStore.collection(fbPosting)
    fun colMeetingStudentPosting() = fireStore.collection(fbStudentPosting)
    fun colMeetingWorkerPosting() = fireStore.collection(fbWorkerPosting)
}