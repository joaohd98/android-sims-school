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
import java.util.*


@Suppress("UNCHECKED_CAST")
class TipsMediasResponse(
    var id: UUID = UUID.randomUUID(),
    var url: String = "",
    var video: String = "",
    var image: String = "",
    var bitmapImage: Bitmap? = null,
    var isVertical: Boolean = false,
    var status: RepositoryStatus = RepositoryStatus.LOADING
) {
    fun initService(result: Map<String, Any?>) {
        url = (result["url"] as? String ?: "")
        video = (result["video"] as? String ?: "")
        image = (result["image"] as? String ?: "")
    }

    fun reset() {
        if(status != RepositoryStatus.SUCCESS) {
            status = RepositoryStatus.LOADING
        }
    }

    fun callService(onSuccess: () -> Unit, onFailed: () -> Unit) {
        this.callImage(onSuccess, onFailed)
    }

    private fun callImage(onSuccess: () -> Unit, onFailed: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val url =
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png"

            bitmapImage = getBitmapFromURL(url)

            if(bitmapImage == null) {
                status = RepositoryStatus.FAILED

                GlobalScope.launch(Dispatchers.Main) {
                    onFailed()
                }
            }
            else {
                isVertical = bitmapImage!!.height > bitmapImage!!.width
                status = RepositoryStatus.SUCCESS

                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)

            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 4000
            connection.connect()

            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }
    }
}

