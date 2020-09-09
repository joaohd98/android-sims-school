package components.uri_image

import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["uri_image", "uri_video"], requireAll = false)
fun setViewModel(view: UriImageView, uriImage: String?, uriVideo: String?) {
    if (uriImage == null && uriVideo == null)
        return

    if(uriImage != "") {
        view.startLoadingImage(uriImage!!)
    }
    else {
        view.startLoadingVideoThumbnail(uriVideo!!)
    }
}