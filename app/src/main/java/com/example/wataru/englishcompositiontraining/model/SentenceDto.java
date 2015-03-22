package com.example.wataru.englishcompositiontraining.model;

/**
 * Created by wataru on 2015/03/21.
 */
public class SentenceDto {
    private int id;
    private String enWord;
    private String jaWord;
    private int level;
    private int tense;
    private int category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }

    public String getJaWord() {
        return jaWord;
    }

    public void setJaWord(String jaWord) {
        this.jaWord = jaWord;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTense() {
        return tense;
    }

    public void setTense(int tense) {
        this.tense = tense;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
