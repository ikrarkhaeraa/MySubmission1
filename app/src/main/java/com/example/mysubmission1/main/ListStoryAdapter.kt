package com.example.mysubmission1.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysubmission1.databinding.ItemRowStoryBinding
import com.example.mysubmission1.main.activity.DetailStoryActivity
import com.example.mysubmission1.main.response.ListStoryItem

class ListStoryAdapter(private val listStory: List<ListStoryItem>) : RecyclerView.Adapter<ListStoryAdapter.ListViewHolder>() {

//    private var onItemClickCallback: OnItemClickCallback? = null
//
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
//        this.onItemClickCallback = onItemClickCallback
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowStoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStory[position])
//        holder.itemView.setOnClickListener {
//            onItemClickCallback?.onItemClicked(listStory[holder.adapterPosition])
//        }
    }

    override fun getItemCount() = listStory.size


    inner class ListViewHolder(var binding: ItemRowStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userData:ListStoryItem){
            binding.apply{
                Glide.with(itemView)
                    .load(userData.photoUrl)
                    //.circleCrop()
                    .into(binding.ivImage)
                binding.tvNamauser.text = userData.name
            }
            itemView.setOnClickListener{
                val intentToDetail = Intent(itemView.context, DetailStoryActivity::class.java)
                intentToDetail.putExtra("DATA", userData)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }

//    fun setData(users: List<ListStoryItem>){
//        listStory.addAll(users)
//        notifyDataSetChanged()
//    }

//    interface OnItemClickCallback {
//        fun onItemClicked(userData: ListStoryItem)
//    }
}