package com.mohammedoucif.attraxiatest.core.di.datasource.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { Firebase.firestore }
}