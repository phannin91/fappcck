package com.app.instagram.data

import androidx.lifecycle.LiveData
import com.app.instagram.models.SearchPost
import com.google.android.gms.tasks.Task

interface SearchRepository {
    fun searchPosts(text: String): LiveData<List<SearchPost>>
    fun createPost(post: SearchPost): Task<Unit>
}