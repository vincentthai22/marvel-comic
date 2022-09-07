package com.dupie.marvelapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dupie.marvelapp.databinding.FragmentMarvelDetailBinding
import com.dupie.marvelapp.di.Provider

class MarvelDetailFragment: Fragment() {

    private var _binding: FragmentMarvelDetailBinding? = null
    private val binding: FragmentMarvelDetailBinding
        get() = _binding!!

    val viewModel: MarvelDetailViewModel by viewModels { Provider.viewModelFactory }
    val args by navArgs<MarvelDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate(args.comicId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMarvelDetailBinding.inflate(inflater, container, false).apply {
            _binding = this
            viewModel.uiStateLD.observe(viewLifecycleOwner) { onDetailUiStateChanged(it) }
        }.root
    }

    private fun onDetailUiStateChanged(uiState: MarvelDetailViewModel.DetailUiState) {
        Glide.with(requireContext())
            .load(uiState.coverImageUrl)
            .into(binding.detailCoverImage)

        binding.comicTitleTextView.text = uiState.comicTitle
        binding.comicDescriptionText.text = uiState.comicDescription
    }



}