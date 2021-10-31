package com.example.marvelcomics.view.comic_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcomics.IBindingRecyclerAdapter
import com.example.marvelcomics.databinding.ItemComicBinding
import com.example.marvelcomics.getItemImageComic
import com.example.marvelcomics.retrofit.response.ComicDetail
import com.example.marvelcomics.view.comic_list.ComicListViewModel

class ComicsListAdapter(private val listener: ComicAdapterListener) :
    RecyclerView.Adapter<ComicsListAdapter.ComicViewHolder>(),
    IBindingRecyclerAdapter<ArrayList<ComicDetail>> {

    interface ComicAdapterListener {
        fun onComicClicked(view: View, comicDetail: ComicDetail)
    }

    var comicsList = ArrayList<ComicDetail>()

    class ComicViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        with(holder.binding) {
            listenerLayout = listener
            comicDetail = comicsList[position]
            urlImage = getItemImageComic(comicsList[position].thumbnail)
        }
    }

    override fun getItemCount(): Int = comicsList.size


    override fun setData(data: ArrayList<ComicDetail>?) {
        if (data != null) {
            comicsList = data
            notifyItemChanged(itemCount)
        }
    }

}