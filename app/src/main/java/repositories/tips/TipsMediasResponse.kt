package repositories.tips

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.widget.MediaController;
import android.net.Uri
import android.widget.VideoView
import com.joao.simsschool.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repositories.RepositoryStatus
import screens.logged.tabs.tips.modal_medias.components.MediaContentView
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
    var videoController: MediaController? = null,
    var durationVideo: Int? = 0,
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

    fun callService(context: Context, contentView: MediaContentView, onSuccess: () -> Unit, onFailed: () -> Unit) {
        if(image != "") {
            this.callImage(onSuccess, onFailed)
        } else {
            this.callVideo(context, contentView, onSuccess, onFailed)
        }
    }

    private fun callVideo(context: Context, contentView: MediaContentView, onSuccess: () -> Unit, onFailed: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val link = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
                val video = Uri.parse(link)
                val mediaPlayer = MediaPlayer.create(context, video)

                durationVideo = mediaPlayer.duration
                isVertical = mediaPlayer.videoHeight > mediaPlayer.videoHeight

                val videoView: VideoView = if(isVertical) {
                    contentView.findViewById(R.id.modal_medias_item_content_video_vertical)
                } else {
                    contentView.findViewById(R.id.modal_medias_item_content_video_horizontal)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    videoView.setVideoURI(video)
                    videoView.requestFocus()
                    videoView.start()
                    onSuccess()
                }

                try {
                    videoView.setOnPreparedListener {}
                } catch (e: Exception) {
                    GlobalScope.launch(Dispatchers.Main) {
                        onFailed()
                    }
                }
            } catch (e: Exception) {
                GlobalScope.launch(Dispatchers.Main) {
                    onFailed()
                }
            }
        }
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

