package com.example.wataru.englishcompositiontraining.dao;

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
}
