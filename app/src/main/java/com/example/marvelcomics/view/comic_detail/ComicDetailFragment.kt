package com.example.marvelcomics.view.comic_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvelcomics.MainActivity
import com.example.marvelcomics.databinding.FragmentComicDetailBinding
import com.example.marvelcomics.getDetailImageComic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment : Fragment() {

    private lateinit var binding: FragmentComicDetailBinding

    private val viewModel by viewModels<ComicDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicDetailBinding.inflate(inflater, container, false).apply {
            viewModelLayout = viewModel
            lifecycleOwner = this@ComicDetailFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyObservers()
        viewModel.getDetailComic(376)
    }

    private fun applyObservers() = viewModel.apply {
        comicsListResponse.observe(requireActivity(), {
            if (it != null) {
                changeTittleToolbar(it.data.results[0].title)
                binding.urlImage = getDetailImageComic(it.data.results[0].thumbnail)
            }
        })
    }

    private fun changeTittleToolbar(title: String?) {
        when (activity) {
            is MainActivity -> (activity as MainActivity).title = title
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        changeTittleToolbar("")
    }
}