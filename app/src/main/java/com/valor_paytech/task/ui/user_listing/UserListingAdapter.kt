package com.valor_paytech.task.ui.user_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valor_paytech.task.databinding.ListItemUserListingBinding
import com.valor_paytech.task.repository.model.Data
import com.valor_paytech.task.repository.model.Users
import com.valor_paytech.task.utils.load

class UserListingAdapter(private var userList: MutableList<Users>) :
    RecyclerView.Adapter<UserListingAdapter.ViewHolder>() {

    var onDetailClicked: ((Users,Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ListItemUserListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int = userList.size

    inner class ViewHolder(private val itemBinding: ListItemUserListingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onDetailClicked?.invoke(userList[adapterPosition],adapterPosition)
            }
        }

        fun bindView(users: Users) {
            itemBinding.tvUserName.text = users.username
            itemBinding.tvUserEmail.text = users.email.toString()
            itemBinding.tvName.text = users.name.toString()
        }
    }

    fun updateList(userList: MutableList<Users>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}
