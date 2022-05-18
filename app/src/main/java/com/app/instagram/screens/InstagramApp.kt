package com.app.instagram.screens

import android.app.Application
import com.app.instagram.common.firebase.FirebaseAuthManager
import com.app.instagram.data.firebase.FirebaseFeedPostsRepository
import com.app.instagram.data.firebase.FirebaseNotificationsRepository
import com.app.instagram.data.firebase.FirebaseSearchRepository
import com.app.instagram.data.firebase.FirebaseUsersRepository
import com.app.instagram.screens.notifications.NotificationsCreator
import com.app.instagram.screens.search.SearchPostsCreator

class InstagramApp : Application() {
    val usersRepo by lazy { FirebaseUsersRepository() }
    val feedPostsRepo by lazy { FirebaseFeedPostsRepository() }
    val notificationsRepo by lazy { FirebaseNotificationsRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val searchRepo by lazy { FirebaseSearchRepository() }

    override fun onCreate() {
        super.onCreate()
        NotificationsCreator(notificationsRepo, usersRepo, feedPostsRepo)
        SearchPostsCreator(searchRepo)
    }
}