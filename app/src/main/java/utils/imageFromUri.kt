package utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.joao.simsschool.R

@BindingAdapter("image_uri")
fun loadImageWithUri(imageView: ImageView, imageUri: String?){
    if (imageUri != null) {
        Glide.with(imageView.context).load(Uri.parse(imageUri)).placeholder(R.drawable.skeleton).into(imageView)
    }
}