# getwallpaperdrawable

## Problem:

With `targetSdk 33` on **Android 13** it is not possible to request
`Manifest.permission.READ_EXTERNAL_STORAGE` but this permission is still required by
[WallpaperManager#getDrawable()](https://developer.android.com/reference/android/app/WallpaperManager#getDrawable())

This example works fine on Android 12, but on Android 13 the permission is automatically denied and
the permission dialog does not pop up.

If the requested permission is changed from `Manifest.permission.READ_EXTERNAL_STORAGE` to
`Manifest.permission.READ_MEDIA_IMAGES` as suggested [in the documentation](https://developer.android.com/about/versions/13/behavior-changes-13#granular-media-permissions) 
then a permission dialog appears, but after allowing the permission `WallpaperManager.getDrawable()`
fails with following error:

```log
com.github.cvzi.getwallpaperdrawable V/MainActivity: Permission granted
com.github.cvzi.getwallpaperdrawable E/MainActivity: java.lang.SecurityException: Permission android.permission.READ_EXTERNAL_STORAGE denied for package com.github.cvzi.getwallpaperdrawable
        at android.os.Parcel.createExceptionOrNull(Parcel.java:3011)
        at android.os.Parcel.createException(Parcel.java:2995)
        at android.os.Parcel.readException(Parcel.java:2978)
        at android.os.Parcel.readException(Parcel.java:2920)
        at android.app.IWallpaperManager$Stub$Proxy.getWallpaperWithFeature(IWallpaperManager.java:895)
        at android.app.WallpaperManager$Globals.getCurrentWallpaperLocked(WallpaperManager.java:667)
        at android.app.WallpaperManager$Globals.peekWallpaperBitmap(WallpaperManager.java:563)
        at android.app.WallpaperManager$Globals.peekWallpaperBitmap(WallpaperManager.java:538)
        at android.app.WallpaperManager.getDrawable(WallpaperManager.java:791)
        at com.github.cvzi.getwallpaperdrawable.MainActivity.showWallpaper(MainActivity.kt:65)
        at com.github.cvzi.getwallpaperdrawable.MainActivity.onRequestPermissionsResult(MainActivity.kt:52)
        at android.app.Activity.dispatchRequestPermissionsResult(Activity.java:8754)
        at android.app.Activity.dispatchActivityResult(Activity.java:8612)
        at android.app.ActivityThread.deliverResults(ActivityThread.java:5337)
        at android.app.ActivityThread.handleSendResult(ActivityThread.java:5383)
        at android.app.servertransaction.ActivityResultItem.execute(ActivityResultItem.java:67)
        at android.app.servertransaction.ActivityTransactionItem.execute(ActivityTransactionItem.java:45)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2306)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loopOnce(Looper.java:201)
        at android.os.Looper.loop(Looper.java:288)
        at android.app.ActivityThread.main(ActivityThread.java:7892)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)
     Caused by: android.os.RemoteException: Remote stack trace:
        at android.os.storage.StorageManager.checkPermissionAndAppOp(StorageManager.java:1805)
        at android.os.storage.StorageManager.checkPermissionAndAppOp(StorageManager.java:1782)
        at android.os.storage.StorageManager.checkPermissionAndAppOp(StorageManager.java:1849)
        at android.os.storage.StorageManager.checkExternalStoragePermissionAndAppOp(StorageManager.java:1965)
        at android.os.storage.StorageManager.checkPermissionReadImages(StorageManager.java:1931)
```

## Screenshots

| Android 12 | Android 13 Beta 3.2 |
| --- | ---- |
| <img src="/screenshots/Android 12.webp" alt="Screenshot Android 12" /> | <img src="/screenshots/Android 13.png" alt="Screenshot Android 13" /> |
