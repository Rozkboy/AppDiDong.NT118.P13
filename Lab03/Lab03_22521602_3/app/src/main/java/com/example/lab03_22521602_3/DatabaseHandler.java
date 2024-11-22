package com.example.lab03_22521602_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sinhVienManager";

    // SinhVien table
    private static final String TABLE_SINHVIENS = "sinhviens";
    private static final String KEY_ID = "id";
    private static final String KEY_HOTEN = "hoTen";
    private static final String KEY_MSSV = "mssv";
    private static final String KEY_LOP = "lop";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SINHVIEN_TABLE = "CREATE TABLE " + TABLE_SINHVIENS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_HOTEN + " TEXT,"
                + KEY_MSSV + " TEXT,"
                + KEY_LOP + " TEXT" + ")";
        db.execSQL(CREATE_SINHVIEN_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIENS);
        // Create tables again
        onCreate(db);
    }

    // them SinhVien
    public void addSinhVien(SinhVien sinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTEN, sinhVien.getHoTen());
        values.put(KEY_MSSV, sinhVien.getMSSV());
        values.put(KEY_LOP, sinhVien.getLop());

        // Inserting Row
        db.insert(TABLE_SINHVIENS, null, values);
        db.close(); // Closing database connection
    }

    // chon SinhVien
    public SinhVien getSinhVien(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SINHVIENS, new String[]{KEY_ID, KEY_HOTEN, KEY_MSSV, KEY_LOP},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SinhVien sinhVien = new SinhVien(
                cursor.getString(1),  // HoTen
                cursor.getString(2),  // MSSV
                cursor.getString(3)   // Lop
        );
        cursor.close();
        return sinhVien;
    }

    // lay tat ca SinhViens
    public List<SinhVien> getAllSinhViens() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SINHVIENS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
                sinhVien.setHoTen(cursor.getString(cursor.getColumnIndexOrThrow(KEY_HOTEN)));
                sinhVien.setMSSV(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MSSV)));
                sinhVien.setLop(cursor.getString(cursor.getColumnIndexOrThrow(KEY_LOP)));
                sinhVienList.add(sinhVien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sinhVienList;
    }

    // cap nhat thay doi SinhVien
    public int updateSinhVien(SinhVien sinhVien, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTEN, sinhVien.getHoTen());
        values.put(KEY_MSSV, sinhVien.getMSSV());
        values.put(KEY_LOP, sinhVien.getLop());

        // cap nhat lai hang
        return db.update(TABLE_SINHVIENS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // xoa 1 trong SinhVien
    public void deleteSinhVien(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SINHVIENS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
