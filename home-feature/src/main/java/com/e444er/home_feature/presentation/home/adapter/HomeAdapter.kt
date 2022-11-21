package com.e444er.home_feature.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.home_feature.R
import com.e444er.home_feature.databinding.ItemLayoutBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class DifferCallback : DiffUtil.ItemCallback<MovieListDomainModel>(){
        override fun areItemsTheSame(
            oldItem: MovieListDomainModel,
            newItem: MovieListDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListDomainModel,
            newItem: MovieListDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer<MovieListDomainModel>(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("PrivateResource")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val movieId = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root)
                .load(movieId.posterPath)
                .error(com.google.android.material.R.drawable.ic_mtrl_checked_circle)
                .centerCrop()
                .into(imagePoster)
            textTitle.text = movieId.title
            textVoteCount.text = movieId.voteAverage.toString()
            textDuration.text = movieId.releaseDate
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}