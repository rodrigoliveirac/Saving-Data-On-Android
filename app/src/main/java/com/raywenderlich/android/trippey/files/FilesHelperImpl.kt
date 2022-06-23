package com.raywenderlich.android.trippey.files

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FilesHelperImpl(private val directory: File) : FilesHelper {

    override fun saveData(fileName: String, data: String) {
        val file = buildFile(fileName)
        val fileOutputStream = buildOutputStream(file)

        try {
            fileOutputStream.use {
                it.write(data.toByteArray())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun getData(): List<File> =
        directory.listFiles()?.toList() ?: emptyList()

    override fun deleteData(fileName: String) {
        val targetFile = buildFile(fileName)
        if(targetFile.exists()) {
            targetFile.delete()
        }
    }

    private fun buildFile(fileName: String): File {
        return File(directory, fileName)
    }

    private fun buildOutputStream(file: File): FileOutputStream {
        return FileOutputStream(file)
    }
}