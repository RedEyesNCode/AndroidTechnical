package com.redeyesncode.androidtechnical.ui.activity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.databinding.ItemUserPostBinding
import com.redeyesncode.androidtechnical.room.PostTable

class FavPostAdapter(var context: Context, var dataList:List<PostTable>):
    RecyclerView.Adapter<FavPostAdapter.MyViewHolder>() {

    lateinit var binding: ItemUserPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = ItemUserPostBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]

        holder.binding.apply {

            tvPostId.text = "Post ID ${data.apiId.toString()}"
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