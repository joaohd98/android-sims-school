package screens.logged.home.components

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.BuildConfig
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.fragment_home_change_picture.*
import utils.alertDialog
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeChangePictureFragment : BottomSheetDialogFragment() {
    private lateinit var currentPhotoPath: String
    var onSuccess: ((bitMap: Bitmap) -> Unit)? = null

    companion object {
        private val permissionsCamera = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        private const val permissionCamera  = 100
        private const val takeCamera  = 1

        private val permissionsGallery = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
        private const val permissionGallery = 200
        private const val takeGallery = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_change_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_home_change_picture_camera.setOnClickListener {
            callPermissions(permissionsCamera, takeCamera) {
                takePictureCamera()
            }
        }

        fragment_home_change_picture_gallery.setOnClickListener {
            callPermissions(permissionsGallery, takeGallery)  {
                takePictureGallery()
            }
        }

        fragment_home_change_picture_close.setOnClickListener {
            Handler().postDelayed({
                dismiss()
            }, 200)
        }
    }

    private fun callPermissions(
        permissions: Array<String>,
        permissionCode: Int,
        onSuccess: () -> Unit
    ) {
        val activity = requireActivity()

        if (permissions.all { ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED }) {
            onSuccess()
        }
        else {
            requestPermissions(permissions, permissionCode)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            permissionCamera -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    takePictureCamera()
                } else {
                    showNoPermissionAlert()
                }
            }
            permissionGallery -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    takePictureGallery()
                } else {
                    showNoPermissionAlert()
                }
            }
            else -> { }
        }
    }

    private fun takePictureCamera() {
        val activity = requireActivity()

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity.packageManager)?.also {
                val photoFile: File? = try {
                    saveImage()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        activity, BuildConfig.APPLICATION_ID, it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, takeCamera)
                }
            }
        }
    }

    private fun takePictureGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, takeGallery)
    }

    @Throws(IOException::class)
    private fun saveImage(): File {
        val activity = requireActivity()

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
        val storageDir: File = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "Sims_Schools_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun showNoPermissionAlert() {
        activity?.alertDialog("We need your permission to change your profile picture")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK) {
            if (requestCode == takeCamera) {
                dismiss()
                callSuccess(BitmapFactory.decodeFile(currentPhotoPath))
            }

            if (requestCode == takeGallery) {
                dismiss()

                val uri = data!!.data!!
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
                callSuccess(bitmap)
            }
        }
    }

    private fun callSuccess(bitmap: Bitmap?) {
        if (bitmap != null) {
            onSuccess?.let { it(bitmap) }
        }
    }
}