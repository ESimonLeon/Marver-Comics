package com.example.marvelcomics.view.comic_detail

import android.app.usage.UsageEvents.Event.NONE
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvelcomics.R
import com.example.marvelcomics.databinding.FragmentComicDetailBinding
import com.example.marvelcomics.getDetailImageComic
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment : Fragment() {

    private val args: ComicDetailFragmentArgs by navArgs()

    private val comicId: Long by lazy(NONE) { args.comicId }

    private lateinit var binding: FragmentComicDetailBinding

    private val viewModel by viewModels<ComicDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fNavHost
            duration = 300
            scrimColor = Color.TRANSPARENT
        }

    }

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
        viewModel.getDetailComic(comicId.toInt())
    }

    private fun applyObservers() = viewModel.apply {
        comicsListResponse.observe(requireActivity(), {
            if (it != null) {
                binding.urlImage = getDetailImageComic(it.data.results[0].thumbnail)
            }
        })
    }


}