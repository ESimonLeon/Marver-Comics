package com.example.marvelcomics

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.retrofit.response.ComicCollection
import com.example.marvelcomics.retrofit.response.ComicPrices

interface IBindingRecyclerAdapter<T> {
    fun setData(data: T?)
}


@BindingAdapter("adapterList")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView?, data: T?) {
    if (recyclerView?.adapter is IBindingRecyclerAdapter<*>) {
        (recyclerView.adapter as IBindingRecyclerAdapter<T>).setData(data)
    }
}

@BindingAdapter("imageUri")
fun bindImageFromUrl(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_broken_image).dontAnimate()
            .into(view)
    }
}

@BindingAdapter("textDescription")
fun setTextDescrition(textView: TextView?, description: String?) {
    textView?.text =
        if (description.isNullOrEmpty()) textView?.context?.getString(R.string.detail_not_description)
        else description
}

@BindingAdapter("textDigital")
fun setTextDigital(textView: TextView?, digital: Int?) {
    if (digital != null) {
        textView?.apply {
            text = context?.getString(R.string.detail_digital)
                .plus(if (digital == 0) context?.getString(R.string.no) else context?.getString(R.string.yes))
        }
    }
}

@BindingAdapter("textFormat")
fun setTextFormat(textView: TextView?, format: String?) {
    textView?.visibility = if (format.isNullOrEmpty()) View.GONE
    else {
        textView?.text = textView?.context?.getString(R.string.detail_format, format)
        View.VISIBLE
    }
}

@BindingAdapter("textPageCount")
fun setTextPageCount(textView: TextView?, count: Int?) {
    textView?.visibility = if (count != null && count > 0) {
        textView?.text = count.toString()
        View.VISIBLE
    } else View.GONE
}


@BindingAdapter("llPrices")
fun setLinearLayoutPrices(linearLayout: LinearLayout?, prices: ArrayList<ComicPrices>?) {
    linearLayout?.visibility = if (prices != null && prices.size > 0) {
        prices.forEach { item ->
            val textView = TextView(linearLayout?.context)
            textView.text = item.type.plus(": $${item.price}")
            linearLayout?.addView(textView)
        }
        View.VISIBLE
    } else View.GONE
}

@BindingAdapter("llCreators")
fun setLinearLayoutCreators(linearLayout: LinearLayout?, creator: ComicCollection?) {
    linearLayout?.visibility = if (creator != null && creator.items.size > 0) {
        creator.items.forEach { item ->
            val textView = TextView(linearLayout?.context)
            textView.text = item.name.plus(": ${item.role}")
            linearLayout?.addView(textView)
        }
        View.VISIBLE
    } else View.GONE
}




