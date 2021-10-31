package com.example.marvelcomics.view.comic_list

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.marvelcomics.R
import com.example.marvelcomics.databinding.FragmentComicListBinding
import com.example.marvelcomics.view.comic_list.adapter.ComicsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicListFragment : Fragment() {

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
        setHasOptionsMenu(true)

        createRecyclerView()
        applyObservers()
    }

    private fun createRecyclerView() = binding.rvList.apply {
        val adapterList = ComicsListAdapter(viewModel)
        adapter = adapterList
        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)!!)
        addItemDecoration(dividerItemDecoration)
    }

    private fun applyObservers() = viewModel.apply {
        communicationResult.observe(requireActivity(), {
            if (it == false) showCommunicationError()
        })
    }

    private fun showCommunicationError() = AlertDialog.Builder(requireContext()).apply {
        setTitle(getString(R.string.title_error))
        setMessage(getString(R.string.description_error))
        setNegativeButton(getString(R.string.negative_button_error)) { dialog, _ ->
            dialog.dismiss()
        }
        create()
        show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_list_comic, menu)
        (activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager).let {
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(it.getSearchableInfo(activity?.componentName))
            }
        }
    }
}