package com.dupie.marvelapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dupie.marvelapp.databinding.FragmentMarvelListBinding
import com.dupie.marvelapp.di.Provider

class MarvelListFragment: Fragment() {

    private val viewModel by viewModels<MarvelListViewModel> { Provider.viewModelFactory }

    private var _binding: FragmentMarvelListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMarvelListBinding.inflate(inflater, container, false).apply {
            this@MarvelListFragment._binding = this
            viewModel.onCreate()
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}