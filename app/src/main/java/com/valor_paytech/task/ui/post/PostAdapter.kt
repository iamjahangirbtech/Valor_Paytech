package com.valor_paytech.task.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valor_paytech.task.databinding.ListItemPostBinding
import com.valor_paytech.task.databinding.ListItemUserListingBinding
import com.valor_paytech.task.repository.model.*
import com.valor_paytech.task.utils.TimeAgo
import com.valor_paytech.task.utils.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostAdapter (private var postList: MutableList<Posts>,var postViewModel: PostViewModel) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var onDetailClicked: ((Posts,Int) -> Unit)? = null
    var onRefreshClicked: ((Posts,Int) -> Unit)? = null
    var onCommentsCall: ((Posts,Int) -> Unit)? = null
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ListItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = postList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int = postList.size

    inner class ViewHolder(private val itemBinding: ListItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onDetailClicked?.invoke(postList[adapterPosition],adapterPosition)
            }
            itemBinding.ivRefresh.setOnClickListener {
                onRefreshClicked?.invoke(postList[adapterPosition],adapterPosition)
            }
        }

        fun bindView(posts: Posts) {
            itemBinding.tvTitle.text = posts.title
            itemBinding.tvDesc.text =  posts.body
            itemBinding.ivPost.load(itemBinding.root.context,posts.image!!)
            var timeAgo = TimeAgo()
            itemBinding.tvLastUpdatedOn.text =  timeAgo.getTimeAgo(posts.created_at!!)

            commentAdapter = CommentAdapter(posts.comments)
            itemBinding.rvComments.adapter = commentAdapter

            onCommentsCall?.invoke(postList[adapterPosition],adapterPosition)
        }
    }

    fun updateList(postList: MutableList<Posts>) {
        this.postList = postList
        notifyDataSetChanged()
    }
}
