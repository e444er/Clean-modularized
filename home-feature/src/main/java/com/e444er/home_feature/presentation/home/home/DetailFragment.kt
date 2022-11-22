package com.e444er.home_feature.presentation.home.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.e444er.domain.model.MovieListDomainModel
import com.e444er.home_feature.R
import com.e444er.home_feature.databinding.DetailFragmentBinding
import com.e444er.home_feature.presentation.home.adapter.HomeAdapter.Companion.IMAGE_URSL
import com.e444er.home_feature.presentation.home.viewBinding

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding: DetailFragmentBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()

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