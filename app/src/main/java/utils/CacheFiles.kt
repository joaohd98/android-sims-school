package utils

import android.content.Context
import android.util.Log
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL

object CacheVideoTemp {
    private var folders = hashSetOf<String>()

    fun saveFile(context: Context, src: String, saveName: String, folder: String): String? {
        folders.add(folder)

        val url = URL(src)
        val client = OkHttpClient()

        val (fileName, prefix ) = saveName.split(".")
        val dirName = File("${context.cacheDir}/$folder").apply {
            deleteOnExit()

            if (!exists()) {
                mkdirs()
            }
        }

        val mediaFile = File.createTempFile(fileName, ".${prefix}", dirName)
        val call = client.newCall(Request.Builder().url(url).get().build())

        return try {
            val response: Response = call.execute()
            if (response.code() == 200 || response.code() == 201) {
                var inputStream: InputStream? = null
                try {
                    inputStream = response.body().byteStream()
                    val buff = ByteArray(1024 * 4)
                    var downloaded: Long = 0
                    val target = response.body().contentLength()

                    mediaFile.apply {
                        deleteOnExit()
                    }

                    val output = FileOutputStream(mediaFile)

                    while (true) {
                        val readed = inputStream.read(buff)
                        if (readed == -1) {
                            break
                        }
                        output.write(buff, 0, readed)
                        downloaded += readed.toLong()
                    }

                    output.flush()
                    output.close()

                    return if (downloaded == target)
                        mediaFile.toString()
                    else
                        null
                } catch (_: IOException) {
                    null
                } finally {
                    inputStream?.close()
                }
            } else {
                null
            }
        } catch (_: IOException) {
            null
        }
    }

    fun deleteAllCache(context: Context) {
        folders.forEach {
            val folder = File(context.cacheDir, it)

            try {
                val files = folder.listFiles()!!
                for (f in files) {
                    f.delete()
                }
            } catch (e: Exception) { }
        }

        folders.clear()
    }
}
