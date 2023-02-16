package com.valor_paytech.task.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valor_paytech.task.databinding.ListItemCommentBinding
import com.valor_paytech.task.repository.model.*

class CommentAdapter(private var comments: MutableList<Comments>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var onDetailClicked: ((Comments,Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ListItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = comments[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int = comments.size

    inner class ViewHolder(private val itemBinding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onDetailClicked?.invoke(comments[adapterPosition],adapterPosition)
            }
        }

        fun bindView(comments: Comments) {
            itemBinding.tvName.text = comments.name
            itemBinding.tvComments.text =  comments.body


        }
    }

    fun updateList(comments: MutableList<Comments>) {
        this.comments = comments
        notifyDataSetChanged()
    }
}
