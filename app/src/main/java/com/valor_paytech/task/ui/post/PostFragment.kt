package com.valor_paytech.task.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.valor_paytech.task.base.BaseFragment
import com.valor_paytech.task.databinding.FragmentPostBinding
import com.valor_paytech.task.repository.model.Posts
import com.valor_paytech.task.utils.extensions.gone
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>() {

    private val productListViewModel: PostViewModel by activityViewModels()
    private lateinit var postAdapter: PostAdapter
    private var postsModel : MutableList<Posts> = ArrayList()
    var userId :Int =-1
    var imagesList: List<String> = listOf(
        "https://images.pexels.com/photos/3408744/pexels-photo-3408744.jpeg?auto=compress&cs=tinysrgb&w=600",
        "https://images.pexels.com/photos/3244513/pexels-photo-3244513.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://cdn.pixabay.com/photo/2014/02/27/16/10/flowers-276014__340.jpg",
        "https://images.pexels.com/photos/33041/antelope-canyon-lower-canyon-arizona.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/3586966/pexels-photo-3586966.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    )

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPostBinding = FragmentPostBinding.inflate(inflater, container, false)

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt("arg_1")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        load()
        observeUserList()
    }

    fun onClick(){
        binding?.ivBack?.setOnClickListener {
            back()
        }
    }
    private fun load() {
        binding?.emptyView?.emptyTitle?.text = "No Post Found"
        postAdapter = PostAdapter(postsModel,productListViewModel)
        binding?.rvPosts?.adapter = postAdapter

        postAdapter.onDetailClicked = { post, pos ->

        }

        postAdapter.onRefreshClicked = { post, pos ->
            observeCommentsList(pos,post.id!!,1)
        }
        postAdapter.onCommentsCall = { post, pos ->
            observeCommentsList(pos,post.id!!,0)
        }
    }


    private fun observeUserList() {
        productListViewModel.getPosts(userId)!!.observe(activity!!, Observer { postListResponse ->
            postsModel.clear()
            postListResponse.forEach {
                item -> postsModel.add(Posts(
                id =  item.id!!,
                title =  item.title!!,
                body =  item.body!!,
                image = imagesList.random(),
                userId = item.userId,
                created_at = System.currentTimeMillis()
            ))
            }
            binding?.progressView?.root?.gone()
            println("userListViewModel = ${Gson().toJson(postListResponse)}")
            postAdapter.notifyDataSetChanged()
        })
    }


    private fun observeCommentsList(pos:Int ,postId :Int,type:Int = 0) {
        productListViewModel.getPostsComments(postId)!!.observe(activity!!, Observer { commentsResponse ->
          var createdAt : Long? = null
          var image : String? = null
           when(type){
               0->{
                   createdAt =  postsModel.get(pos).created_at!!
                   image =  imagesList.random()
               }
               1->{
                   createdAt = System.currentTimeMillis()
                   image =  postsModel.get(pos).image!!
               }
           }
            postsModel.set(pos, Posts(
                id =  postsModel.get(pos).id!!,
                title =  postsModel.get(pos).title!!,
                body =  postsModel.get(pos).body!!,
                image = image,
                userId = postsModel.get(pos).userId,
                created_at = createdAt!!,
                comments = commentsResponse
            ))

           // postAdapter.notifyItemChanged(pos)
        })
    }




}
