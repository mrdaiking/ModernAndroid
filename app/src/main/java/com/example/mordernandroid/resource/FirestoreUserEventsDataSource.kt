package com.example.mordernandroid.resource

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirestoreUserEventsDataSource(
//    private val firestore: FirebaseFirestore
) {
//    fun getUserEvents(): Flow<UserEvents> = callbackFlow {
        // Reference to use in Firestore
//        var eventsCollection = CollectionReference? = null
//        try {
//            eventsCollection = FirebaseFirestore.getInstance()
//                .collection("collection")
//                .document("app")
//        } catch (e: Throwable) {
//            close(e)
//        }
//
//        // Registers callback to firestore, which will be called on new events
//        val subscription = eventsCollection?.addSnapshotListener { snapshot, _ ->
//            try {
//                offer(snapshot.getEvents())
//            } catch (e: Throwable) {
//
//            }
//        }
//
//        awaitClose { subscription?.remove}
//    }
}