package com.app.instagram.screens.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.instagram.R
import com.app.instagram.models.Comment
import com.app.instagram.screens.common.SimpleCallback
import com.app.instagram.screens.common.loadUserPhoto
import com.app.instagram.screens.common.setCaptionText
import kotlinx.android.synthetic.main.comments_item.view.*

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private var comments = listOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.comments_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        with(holder.view) {
            photo.loadUserPhoto(comment.photo)
            text.setCaptionText(comment.username, comment.text, comment.timestampDate())
        }
    }

    fun updateComments(newComments: List<Comment>) {
        val diffResult = DiffUtil.calculateDiff(SimpleCallback(comments, newComments) {it.id})
        this.comments = newComments
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = comments.size
}