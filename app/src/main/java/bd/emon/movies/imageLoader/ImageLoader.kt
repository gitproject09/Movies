package bd.emon.movies.imageLoader

import androidx.databinding.BindingAdapter
import bd.emon.movies.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.imageview.ShapeableImageView

object ImageLoader {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ShapeableImageView, imageUrl: String) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .transform(CenterCrop())
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .placeholder(R.drawable.place_holder_w240_h360)
            .error(R.drawable.error_w240_h360)
            .into(imageView)
    }
}
