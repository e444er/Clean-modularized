package com.e444er.favorite_feature.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.favorite_feature.databinding.SearchItemBinding
import com.e444er.favorite_feature.presentation.favorite.search.SearchFragmentDirections

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    inner class SearchHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class DifferCallback : DiffUtil.ItemCallback<MovieListDomainModel>() {
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

    val differ = AsyncListDiffer(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val movieId = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root)
                .load(IMAGE_URSL + movieId?.poster_path)
                .centerCrop()
                .into(imagePopular)
            textPopularTitle.text = movieId.title
            textPopularRating.text = movieId.voteAverage.toString()
            textPopularYear.text = movieId.releaseDate
        }
        holder.binding.root.setOnClickListener {
            if (movieId != null) {
                val action =
                    SearchFragmentDirections.actionFavoriteFragmentToDetailSearchFragment(movieId)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        const val IMAGE_URSL = "https://image.tmdb.org/t/p/w300/"

    }
}