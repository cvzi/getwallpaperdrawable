# getwallpaperdrawable

## Problem:

With `targetSdk 33` on **Android 13** it is not possible to request `Manifest.permission.READ_EXTERNAL_STORAGE` but this permission is still required by [WallpaperManager#getDrawable()](https://developer.android.com/reference/android/app/WallpaperManager#getDrawable())
