package com.e444er.favorite_feature.presentation.favorite.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.e444er.favorite_feature.R
import com.e444er.favorite_feature.databinding.SearchFragmentBinding
import com.e444er.favorite_feature.presentation.favorite.adapter.SearchAdapter
import com.e444er.favorite_feature.presentation.favorite.textChangeFlow
import com.e444er.favorite_feature.presentation.favorite.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val binding: SearchFragmentBinding by viewBinding()
    private val mAdapter by lazy { SearchAdapter() }
    private val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList()
        getSearch()
        editSearch()
    }

    private fun editSearch() {
        binding.editSearch.textChangeFlow()
            .debounce(300)
            .distinctUntilChanged()
            .mapLatest { viewModel.searchName(it) }
            .flowOn(Dispatchers.IO)
            .catch { Toast.makeText(requireContext(), "Search", Toast.LENGTH_LONG).show() }
            .onEach { Log.d("onEach", "onEach is : $it") }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun getSearch() {
        lifecycleScope.launchWhenCreated {
            viewModel.searchMovie.collectLatest { it ->
                if (it.isLoading) {
                    binding.progressBar2.isVisible = true
                    binding.rvSearch.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar2.isVisible = false
                    binding.rvSearch.isVisible = false
                }
                it.data?.let {
                    binding.progressBar2.isVisible = false
                    binding.rvSearch.isVisible = true
                    mAdapter.differ.submitList(it)
                }
            }
        }
    }

    private fun rvList() {
        binding.rvSearch.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}