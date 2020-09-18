package repositories.tips

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.FirebaseInstances
import repositories.RepositoryStatus
import utils.CacheVideoTemp
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


@Suppress("UNCHECKED_CAST")
class TipsMediasResponse(
    var id: UUID = UUID.randomUUID(),
    var url: String = "",
    var image: String = "",
    var video: String = "",
    var firebaseUri: String? = null,
    var absolutePath: Uri? = null,
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
            this.getFile(context, onSuccess, onFailed, true)
        } else {
            this.getFile(context, onSuccess, onFailed, false)
        }
    }

    private fun getFile(
        context: Context,
        onSuccess: () -> Unit,
        onFailed: () -> Unit,
        isImage: Boolean
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val onError = fun() {
                status = RepositoryStatus.FAILED

                GlobalScope.launch(Dispatchers.Main) {
                    onFailed()
                }
            }

            try {
                if(firebaseUri == null) {
                    firebaseUri = if(isImage) {
                        FirebaseInstances.getURLFromMedia(image)
                    }
                    else {
                        FirebaseInstances.getURLFromMedia(video)
                    }
                }

                val (saveName, dirName) = if(isImage)
                    arrayOf(image, "image")
                else
                    arrayOf(video, "video")

                absolutePath = cacheFromUrl(context, firebaseUri!!,  saveName,dirName)

                if(absolutePath == null) {
                    onError()
                }
                else {
                    if(isImage) {
                        val bitmap = BitmapFactory.decodeFile(File(absolutePath?.path!!).absolutePath, null)
                        isVertical = bitmap.height > bitmap.width
                    }
                    else {
                        val mediaPlayer = MediaPlayer.create(context, absolutePath)
                        status = RepositoryStatus.SUCCESS
                        isVertical = mediaPlayer.videoHeight > mediaPlayer.videoWidth
                        videoDuration = mediaPlayer.duration
                    }

                    status = RepositoryStatus.SUCCESS

                    GlobalScope.launch(Dispatchers.Main) {
                        onSuccess()
                    }
                }
            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun cacheFromUrl(context: Context, src: String, saveName: String, folder: String): Uri? {
        val url = CacheVideoTemp.saveFile(context, src, saveName.split("/").last(), folder)

        return if(url != null) {
            Uri.parse(url)
        } else {
            null
        }
    }
}

