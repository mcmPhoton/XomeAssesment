package com.sample.xomeassesment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.xomeassesment.R
import com.sample.xomeassesment.databinding.ItemFlickrPhotosBinding
import com.sample.xomeassesment.model.Photo

class FlickrPhotoAdapter(val photos: List<Photo>) : RecyclerView.Adapter<FlickrPhotoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlickrPhotoAdapter.ViewHolder {
        val binding: ItemFlickrPhotosBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_flickr_photos,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: FlickrPhotoAdapter.ViewHolder, position: Int) {
        val photo = photos.get(position)
        val aString =
            "https://farm" + photo.farm + ".static.flickr.com/" + photo.server + "/" +
                photo.id + "_" + photo.secret + ".jpg"
        Glide.with(holder.itemView.context)
            .load(aString)
            .placeholder(R.color.design_default_color_on_primary)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.ivItem)
    }

    inner class ViewHolder(val binding: ItemFlickrPhotosBinding) :
        RecyclerView.ViewHolder(binding.root)
}
