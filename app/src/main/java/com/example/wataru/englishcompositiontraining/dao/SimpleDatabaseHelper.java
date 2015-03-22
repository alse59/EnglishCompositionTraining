package com.example.wataru.englishcompositiontraining.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wataru on 2015/02/28.
 */
public class SimpleDatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "sample.sqlite";
    private static final int VERSION = 1;

    public SimpleDatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sentences (" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, en_word TEXT, ja_word TEXT, level INTEGER, tense INTEGER, category INTEGER)");
        db.execSQL("INSERT INTO sentences(en_word, ja_word, level, tense, category) VALUES('This is a good book.', 'これは良い本です。', 1, 1, 1)");
        db.execSQL("INSERT INTO sentences(en_word, ja_word, level, tense, category) VALUES('This dictionary is good.', 'この辞書は良い。', 1, 1, 1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {
        db.execSQL("DROP TABLE IF EXISTS sentences");
        onCreate(db);
    }
}
