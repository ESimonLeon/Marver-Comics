package com.example.marvelcomics.view.comic_list

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.marvelcomics.R
import com.example.marvelcomics.databinding.FragmentComicListBinding
import com.example.marvelcomics.retrofit.response.ComicDetail
import com.example.marvelcomics.view.BaseFragment
import com.example.marvelcomics.view.comic_list.adapter.ComicsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicListFragment : BaseFragment(), ComicsListAdapter.ComicAdapterListener {

    private lateinit var binding: FragmentComicListBinding

    private val viewModel by viewModels<ComicListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicListBinding.inflate(inflater, container, false).apply {
            viewModelLayout = viewModel
            lifecycleOwner = this@ComicListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecyclerView()
        applyObservers()
    }

    private fun createRecyclerView() = binding.rvList.apply {
        val adapterList = ComicsListAdapter(this@ComicListFragment)
        adapter = adapterList
        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.item_divider
            )!!
        )
        addItemDecoration(dividerItemDecoration)
    }

    private fun applyObservers() = viewModel.apply {
        communicationResult.observe(requireActivity(), {
            if (it == false) showCommunicationError()
        })
    }

    override fun onComicClicked(view: View, comicDetail: ComicDetail) {
        val extras =
            FragmentNavigatorExtras(view to getString(R.string.comic_detail_mcv_transition_name))
        val directions =
            ComicListFragmentDirections.actionListFragmentToDetailFragment(comicDetail.id.toLong())
        findNavController().navigate(directions, extras)
    }

}