package com.example.myapplication.download

import android.content.Context
import android.os.Environment
import com.blankj.utilcode.util.FileIOUtils
import java.io.File

object DownloadMmg {

    fun downnload(context : Context, assetName : String) {
        val inputSystem = context.assets.open(assetName)

        val externalStoragePublicDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)


        FileIOUtils.writeFileFromIS(File(externalStoragePublicDirectory, assetName), inputSystem)

    }
}