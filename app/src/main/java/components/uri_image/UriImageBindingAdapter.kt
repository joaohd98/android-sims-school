package components.uri_image

import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["uri_image"], requireAll = false)
fun setViewModel(view: UriImageView, uri: String?) {
    if (uri == null)
        return

    view.uri = uri
    view.startLoadingImg()
}