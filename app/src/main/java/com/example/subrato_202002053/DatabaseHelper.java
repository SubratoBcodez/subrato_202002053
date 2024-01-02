package com.example.subrato_202002053;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "circle_data.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "circle_values";
    private static final String COLUMN_RADIUS = "radius";
    private static final String COLUMN_AREA = "area";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_RADIUS + " REAL," +
                    COLUMN_AREA + " REAL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public long insertData(double radius, double area) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RADIUS, radius);
        values.put(COLUMN_AREA, area);

        long newRowId = db.insert(TABLE_NAME, null, values);

        // Close the database connection
        db.close();

        return newRowId;
    }

    public String getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_RADIUS, COLUMN_AREA};

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);

        StringBuilder values = new StringBuilder();
        while (cursor.moveToNext()) {
            double radius = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_RADIUS));
            double area = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AREA));

            values.append(String.format("Radius: %.2f, Area: %.2f\n", radius, area));
        }

        cursor.close();
        db.close();

        return values.toString();
    }
}
