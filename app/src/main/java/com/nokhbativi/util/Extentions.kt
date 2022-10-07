package com.nokhbativi.util

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map

fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
    val listenerRegistration = addSnapshotListener { value, error ->
        if (error != null) {
            close()
            return@addSnapshotListener
        }
        if (value != null)
            trySend(value)
    }
    awaitClose {
        listenerRegistration.remove()
    }
}

inline fun <reified T>get(name: String): Flow<List<T>> {
    val db = Firebase.firestore
    return db.collection(TIVI)
        .document(DATA)
        .collection(name)
        .snapshotFlow()
        .map { querySnapshot ->
            querySnapshot.toObjects(T::class.java)
        }
}