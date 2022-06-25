package com.raywenderlich.android.trippey.database

object DatabaseConstants {

    const val DATABASE_NAME = "Trippey"
    const val DATABASE_VERSION = 2

    /**
     * Table names and column names for the database model.
     */

    const val TRIP_TABLE_NAME = "trips"
    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_COUNTRY = "country"
    const val COLUMN_DETAILS = "details"
    const val COLUMN_IMAGE_URL = "imageUrl"
    const val COLUMN_LOCATIONS = "locations"

    /**
     * Queries to help out with database setup.
     * */

    const val SQL_CREATE_ENTRIES = """
    CREATE TABLE $TRIP_TABLE_NAME
    ($COLUMN_ID TEXT PRIMARY KEY,
     $COLUMN_TITLE TEXT NOT NULL,
     $COLUMN_COUNTRY TEXT NOT NULL DEFAULT '',
     $COLUMN_DETAILS TEXT NOT NULL,
     $COLUMN_IMAGE_URL TEXT
     $COLUMN_LOCATIONS TEXT NOT NULL DEFAULT '')
  """

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TRIP_TABLE_NAME"

    const val SQL_UPDATE_DATABASE_ADD_LOCATIONS =
        "ALTER TABLE $TRIP_TABLE_NAME ADD $COLUMN_LOCATIONS TEXT NOT NULL DEFAULT ''"

    const val QUERY_BY_ID = "$COLUMN_ID LIKE ?"
}