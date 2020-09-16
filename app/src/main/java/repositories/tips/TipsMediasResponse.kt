package repositories.tips

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.RepositoryStatus
import utils.CacheVideoTemp
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


@Suppress("UNCHECKED_CAST")
class TipsMediasResponse(
    var id: UUID = UUID.randomUUID(),
    var url: String = "",
    var image: String = "",
    var imageUri: String? = null,
    var imageBitmap: Bitmap? = null,
    var video: String = "",
    var videoAbsolutePath: Uri? = null,
    var videoDuration: Int? = null,
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

    fun callService(
        context: Context,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        if(image != "") {
            this.getImage(onSuccess, onFailed)
        } else {
            this.getVideo(context, onSuccess, onFailed)
        }
    }

    private fun getVideo(
        context: Context,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val onError = fun () {
                status = RepositoryStatus.FAILED

                GlobalScope.launch(Dispatchers.Main) {
                    onFailed()
                }
            }

            val link = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
            val path = cacheVideoFromUrl(context, link)

            if(path == null) {
                onError()
            }
            else {
                val mediaPlayer = MediaPlayer.create(context, path)

                videoAbsolutePath = path
                status = RepositoryStatus.SUCCESS
                isVertical = mediaPlayer.videoHeight > mediaPlayer.videoHeight
                videoDuration = mediaPlayer.duration

                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    private fun getImage(onSuccess: () -> Unit, onFailed: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val onError = fun () {
                status = RepositoryStatus.FAILED

                GlobalScope.launch(Dispatchers.Main) {
                    onFailed()
                }
            }

            try {
                if(imageUri == null) {
                    imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png"
//                    imageUri = FirebaseInstances.getURLFromMedia(image)!!
                }

                imageBitmap = getBitmapFromURL(imageUri!!)

                if(imageBitmap == null) {
                    onError()
                }
                else {
                    isVertical = imageBitmap!!.height > imageBitmap!!.width
                    status = RepositoryStatus.SUCCESS

                    GlobalScope.launch(Dispatchers.Main) {
                        onSuccess()
                    }
                }

            } catch (e: java.lang.Exception) {
                onError()
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

    private fun cacheVideoFromUrl(context: Context, src: String): Uri? {
        val url = CacheVideoTemp.saveVideo(context, src, video.split("/").last(), "video")

        return if(url != null) {
            Uri.parse(url)
        } else {
            null
        }
    }
}

