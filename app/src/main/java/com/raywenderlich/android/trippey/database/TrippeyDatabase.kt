package com.raywenderlich.android.trippey.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson
import com.raywenderlich.android.trippey.database.DatabaseConstants.DATABASE_NAME
import com.raywenderlich.android.trippey.database.DatabaseConstants.DATABASE_VERSION
import com.raywenderlich.android.trippey.database.DatabaseConstants.SQL_CREATE_ENTRIES
import com.raywenderlich.android.trippey.database.DatabaseConstants.SQL_DELETE_ENTRIES
import com.raywenderlich.android.trippey.model.Trip

class TrippeyDatabase(context: Context, private val gson: Gson) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
        onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun saveTrips(trip: Trip) {
        val database = writableDatabase ?: return
        val newValues = ContentValues().apply {
            put(DatabaseConstants.COLUMN_ID, trip.id)
            put(DatabaseConstants.COLUMN_TITLE, trip.title)
            put(DatabaseConstants.COLUMN_COUNTRY, trip.country)
            put(DatabaseConstants.COLUMN_DETAILS, trip.details)
            put(DatabaseConstants.COLUMN_IMAGE_URL, trip.imageUrl)
        }

        database.insert(DatabaseConstants.TRIP_TABLE_NAME, null, newValues)
    }

    fun updateTrips(trip: Trip) {

    }

    fun deleteTrips(trip: String) {

    }

    fun getTrips(): List<Trip> {
        val items = mutableListOf<Trip>()
        val db = readableDatabase ?: return items

        val cursor = db.query(
            DatabaseConstants.TRIP_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            items.add(
                Trip(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_COUNTRY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_DETAILS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.COLUMN_IMAGE_URL))
                )
            )
        }

        cursor.close()

        return items
    }
}