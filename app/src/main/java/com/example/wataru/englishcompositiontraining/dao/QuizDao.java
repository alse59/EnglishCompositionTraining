package com.example.wataru.englishcompositiontraining.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wataru.englishcompositiontraining.model.SentenceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wataru on 2015/02/28.
 */
public class QuizDao {
    private SimpleDatabaseHelper helper;
    private static final String TABLENAME = "sentences";

    private static final String COL_EN_WORD = "en_word";
    private static final String COL_JA_WORD = "ja_word";
    private static final String COL_LEVEL = "level";
    private static final String COL_TENSE = "tense";
    private static final String COL_CATEGORY = "category";

    public QuizDao(Context context) {
        helper = new SimpleDatabaseHelper(context);
    }

    public List<SentenceDto> searchQuiz(String selection) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<SentenceDto> list = new ArrayList<SentenceDto>();
        String[] columns = {"en_word", "ja_word", "level", "tense", "category"};
        Cursor cs = db.query(TABLENAME,columns, null, null, null, null, null);
        if (cs.moveToFirst()) {
            do {
                SentenceDto dto = new SentenceDto();
                dto.setEnWord(cs.getString(0));
                dto.setJaWord(cs.getString(1));
                dto.setLevel(cs.getInt(2));
                dto.setTense(cs.getInt(3));
                dto.setCategory(cs.getInt(4));
                list.add(dto);
            } while (cs.moveToNext());
        }
        return list;
    }

    public long insert(SentenceDto dto) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_EN_WORD, dto.getEnWord());
        cv.put(COL_JA_WORD, dto.getJaWord());
        cv.put(COL_LEVEL, dto.getLevel());
        cv.put(COL_TENSE, dto.getTense());
        cv.put(COL_CATEGORY, dto.getCategory());

        return db.insert(TABLENAME, null, cv);
    }
}
