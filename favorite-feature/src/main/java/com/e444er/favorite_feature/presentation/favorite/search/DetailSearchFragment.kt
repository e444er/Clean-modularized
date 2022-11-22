package com.e444er.favorite_feature.presentation.favorite.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.favorite_feature.R
import com.e444er.favorite_feature.databinding.SearchDetailFragmentBinding
import com.e444er.favorite_feature.presentation.favorite.adapter.SearchAdapter.Companion.IMAGE_URSL
import com.e444er.favorite_feature.presentation.favorite.viewBinding

class DetailSearchFragment : Fragment(R.layout.search_detail_fragment) {

    private val binding: SearchDetailFragmentBinding by viewBinding()
    private val args: DetailSearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiBind(args.movieId)
    }

    private fun uiBind(movieId: MovieListDomainModel) {
        binding.apply {
            Glide.with(root)
                .load(IMAGE_URSL + movieId.poster_path)
                .into(imageView)

            textSearch.text = movieId.title
            textSearchDate.text = movieId.releaseDate
            textSearchRating.text = movieId.voteAverage.toString()
            textSummary.text = movieId.overview
        }
    }
}