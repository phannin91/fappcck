package com.app.instagram.screens.addfriends

import androidx.lifecycle.LiveData
import com.app.instagram.data.FeedPostsRepository
import com.app.instagram.data.UsersRepository
import com.app.instagram.data.common.map
import com.app.instagram.models.User
import com.app.instagram.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks

class AddFriendsViewModel(onFailureListener: OnFailureListener,
                          private val usersRepo: UsersRepository,
                          private val feedPostsRepo: FeedPostsRepository)
    : BaseViewModel(onFailureListener) {
    val userAndFriends: LiveData<Pair<User, List<User>>> =
            usersRepo.getUsers().map { allUsers ->
                val (userList, otherUsersList) = allUsers.partition {
                    it.uid == usersRepo.currentUid()
                }
                userList.first() to otherUsersList
            }

    fun setFollow(currentUid: String, uid: String, follow: Boolean): Task<Void> {
        return (if (follow) {
            Tasks.whenAll(
                    usersRepo.addFollow(currentUid, uid),
                    usersRepo.addFollower(currentUid, uid),
                    feedPostsRepo.copyFeedPosts(postsAuthorUid = uid, uid = currentUid))
        } else {
            Tasks.whenAll(
                    usersRepo.deleteFollow(currentUid, uid),
                    usersRepo.deleteFollower(currentUid, uid),
                    feedPostsRepo.deleteFeedPosts(postsAuthorUid = uid, uid = currentUid))
        }).addOnFailureListener(onFailureListener)
    }
}