package repositories.tips

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import components.uri_image.GlideApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.RepositoryStatus
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


@Suppress("UNCHECKED_CAST")
class TipsMediasResponse(
    var url: String = "",
    var video: String = "",
    var image: String = "",
    var isVertical: Boolean = false,
    var bitmapImage: Bitmap? = null,
    var status: RepositoryStatus = RepositoryStatus.LOADING
) {
    fun initService(result: Map<String, Any?>) {
        url = (result["url"] as? String ?: "")
        video = (result["video"] as? String ?: "")
        image = (result["image"] as? String ?: "")
    }

    fun callService(context: Context, onSuccess: () -> Unit, onFailed: () -> Unit) {
        this.callImage(context, onSuccess, onFailed)
    }

    private fun callImage(context: Context, onSuccess: () -> Unit, onFailed: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val url =
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png"

            bitmapImage = getBitmapFromURL(url)

            if(bitmapImage == null) {
                GlobalScope.launch(Dispatchers.Main) {
                    status = RepositoryStatus.FAILED
                    onFailed()
                }
            }
            else {
                isVertical = bitmapImage!!.height > bitmapImage!!.width
                GlobalScope.launch(Dispatchers.Main) {
                    status = RepositoryStatus.SUCCESS
                    onSuccess()
                }
            }
        }
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }
    }
}

