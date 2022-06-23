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

    override fun getData(): List<File> {
        TODO("Not yet implemented")
    }

    override fun deleteData(fileName: String) {

    }

    private fun buildFile(fileName: String): File {
        return File(directory, fileName)
    }

    private fun buildOutputStream(file: File): FileOutputStream {
        return FileOutputStream(file)
    }
}