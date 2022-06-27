package com.github.cvzi.getwallpaperdrawable

import android.Manifest
import android.app.Activity
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : Activity() {
    companion object {
        private const val REQUEST_CODE = 123
        private const val PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE // or Manifest.permission.READ_MEDIA_IMAGES ?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this,
                PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION),
                REQUEST_CODE
            )
        } else {
            showWallpaper()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.v("MainActivity", "Permission granted")
                    (findViewById<View>(R.id.textView) as TextView).text = "Permission granted"
                    showWallpaper()
                } else {
                    Log.v("MainActivity", "Permission denied")
                    (findViewById<View>(R.id.textView) as TextView).text = "Permission denied"
                }
                return
            }
        }
    }

    private fun showWallpaper() {
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
        val drawable = try {
            wallpaperManager.drawable
        } catch (e: SecurityException) {
            Log.e("MainActivity", "", e)
            (findViewById<View>(R.id.textView) as TextView).text = e.stackTraceToString()
            null
        }
        drawable?.let {
            (findViewById<View>(R.id.textView) as TextView).text = "Success!"
            (findViewById<View>(R.id.imageView) as ImageView).setImageDrawable(it)
        }
    }
}