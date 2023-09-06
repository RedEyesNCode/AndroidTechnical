package com.redeyesncode.androidtechnical.ui.activity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.data.PostsResponse
import com.redeyesncode.androidtechnical.databinding.ItemUserPostBinding

class CommentAdapter(var context: Context, var dataList:ArrayList<PostCommentResponse>):
    RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {

    lateinit var binding: ItemUserPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = ItemUserPostBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]

        holder.binding.apply {

            tvPostId.text = "Post ID ${data.postId.toString()}"
            tvUserId.text = " ID ${data.id.toString()}"
            tvPostBody.text = "Body ${data.body.toString()}"
            tvPostTitle.text = "Title ${data.body.toString()}"

        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    class MyViewHolder(var binding: ItemUserPostBinding): RecyclerView.ViewHolder(binding.root)
}