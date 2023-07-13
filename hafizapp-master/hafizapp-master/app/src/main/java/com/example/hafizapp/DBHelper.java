package com.example.hafizapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;






import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";
    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABAQI = "sabaqi";
    private static final String COLUMN_MANZIL = "manzil";

    private static final String COLUMN_ID = "id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_SABAQ + " TEXT, " +
                COLUMN_SABAQI + " TEXT, " +
                COLUMN_MANZIL + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_SABAQI, student.getSabaqi());
        values.put(COLUMN_MANZIL, student.getManzil());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
        int classIndex = cursor.getColumnIndex(COLUMN_CLASS);
        int sabaqIndex = cursor.getColumnIndex(COLUMN_SABAQ);
        int sabaqiIndex = cursor.getColumnIndex(COLUMN_SABAQI);
        int manzilIndex = cursor.getColumnIndex(COLUMN_MANZIL);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            int age = cursor.getInt(ageIndex);
            String className = cursor.getString(classIndex);
            String sabaq = cursor.getString(sabaqIndex);
            String sabaqi = cursor.getString(sabaqiIndex);
            String manzil = cursor.getString(manzilIndex);
            Student student = new Student(id, name, age, className, sabaq, sabaqi, manzil);
            students.add(student);
        }

        cursor.close();
        db.close();
        return students;
    }

    public List<String> getAllStudentNames() {
        List<String> studentNames = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME, null);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(nameIndex);
                studentNames.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentNames;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_MANZIL, student.getManzil());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public void updateStudentSabaqManzil(int studentId, String sabaq, String sabaqi, String manzil) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SABAQ, sabaq);
        values.put(COLUMN_SABAQI, sabaqi);
        values.put(COLUMN_MANZIL, manzil);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(studentId)});
        db.close();
    }

    public Student searchStudentByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_NAME + "=?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
            int classIndex = cursor.getColumnIndex(COLUMN_CLASS);
            int sabaqIndex = cursor.getColumnIndex(COLUMN_SABAQ);
            int sabaqiIndex = cursor.getColumnIndex(COLUMN_SABAQI);
            int manzilIndex = cursor.getColumnIndex(COLUMN_MANZIL);

            int id = cursor.getInt(idIndex);
            int age = cursor.getInt(ageIndex);
            String className = cursor.getString(classIndex);
            String sabaq = sabaqIndex != -1 ? cursor.getString(sabaqIndex) : null;
            String sabaqi = sabaqIndex != -1 ? cursor.getString(sabaqiIndex) : null;
            String manzil = manzilIndex != -1 ? cursor.getString(manzilIndex) : null;

            Student student = new Student(id, name, age, className, sabaq, sabaqi, manzil);
            cursor.close();
            return student;
        }
        cursor.close();
        return null;
    }
}