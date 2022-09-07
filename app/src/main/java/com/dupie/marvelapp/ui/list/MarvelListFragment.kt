package com.dupie.marvelapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dupie.marvelapp.databinding.FragmentMarvelListBinding
import com.dupie.marvelapp.di.Provider

class MarvelListFragment : Fragment() {

    private val viewModel: MarvelListViewModel by lazy {
        ViewModelProvider(this, Provider.viewModelFactory)[MarvelListViewModel::class.java]
    }

    private var _binding: FragmentMarvelListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        MarvelListAdapter { comic ->
            viewModel.onComicClicked(comic)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMarvelListBinding.inflate(inflater, container, false).apply {
            this@MarvelListFragment._binding = this
            setupRecyclerView()
            viewModel.listLiveData.observe(viewLifecycleOwner) { adapter.submitList(it) }
            viewModel.navigateToDetailLD.observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(
                        MarvelListFragmentDirections.actionNavigateToDetail(it)
                    )
                }
            }
        }.root
    }

    private fun setupRecyclerView() {
        binding.comicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.comicsRecyclerView.adapter = this@MarvelListFragment.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}