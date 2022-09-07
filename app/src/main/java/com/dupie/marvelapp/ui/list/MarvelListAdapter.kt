package com.dupie.marvelapp.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dupie.marvelapp.databinding.ListItemMarvelBinding

class MarvelListAdapter(
    val onClick: (comic: ComicListItem) -> Unit,
): ListAdapter<ComicListItem, MarvelListAdapter.ListViewHolder>(
    object: DiffUtil.ItemCallback<ComicListItem>() {
        override fun areItemsTheSame(oldItem: ComicListItem, newItem: ComicListItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ComicListItem, newItem: ComicListItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ListItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: ListItemMarvelBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: ComicListItem) {

            binding.root.setOnClickListener {
                onClick(comic)
            }

            Glide.with(binding.root.context)
                .load(comic.comicThumbUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(binding.comicImage)
            Log.d("Urls", comic.comicThumbUrl)

             binding.comicTitle.text = comic.comicTitle
        }

    }

}