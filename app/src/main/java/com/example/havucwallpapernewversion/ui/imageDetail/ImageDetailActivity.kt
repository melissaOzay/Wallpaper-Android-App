package com.example.havucwallpapernewversion.ui.imageDetail

import android.app.DownloadManager
import android.app.WallpaperManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.activity.viewModels
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivityImageDetailBinding
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.UUID


@AndroidEntryPoint
class ImageDetailActivity : BaseActivity<ImageDetailVM, ActivityImageDetailBinding>() {

    override val viewModel: ImageDetailVM by viewModels()

    override fun getViewBinding(): ActivityImageDetailBinding {
        return ActivityImageDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        intent.let {
            val imageUrl = intent.getStringExtra("imageUrl")
            val likeOrUnLike = intent.getBooleanExtra("likeOrUnlike", false)
            val id = intent.getStringExtra("id")
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(binding.ivPhoto, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {}

                    override fun onError(e: java.lang.Exception?) {
                        Picasso.get()
                            .load(imageUrl)
                            .into(binding.ivPhoto)
                    }
                })
            with(binding) {
                ivFavorite.isChecked = likeOrUnLike
                ivFavorite.setOnClickListener {
                    viewModel.likeOrUnLike(
                        Image(
                            id.toString(), path = imageUrl.toString(),
                            imagePullPath = imageUrl.toString(),
                            isLiked = it.isActivated
                        )
                    )
                }
                ivSetting.setOnClickListener {
                    setWallpaper(imageUrl.toString())
                }
                ivDownload.setOnClickListener {
                    downloadImageNew(imageUrl.toString())
                }
            }
        }

    }

    private fun setWallpaper(url: String) {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val wpm = WallpaperManager.getInstance(this)
        val ins: InputStream?
        try {
            ins =
                URL(url).openStream()
            wpm.setStream(ins)
            Toast.makeText(this, "Your home screen picture has changed.", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun downloadImageNew(downloadUrlOfImage: String) {
        val fileName = "Wallpaper_${UUID.randomUUID()}.jpeg"
        try {
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(fileName)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + fileName + ".jpg"
                )
            dm.enqueue(request)
            Toast.makeText(this, "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show()
        }
    }
}