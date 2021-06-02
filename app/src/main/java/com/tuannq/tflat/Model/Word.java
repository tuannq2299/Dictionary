package com.tuannq.tflat.Model;

import java.io.Serializable;

public class Word implements Serializable {
    private int id;
    private String word;
    private String mean;
    private String examp;

    public Word() {
        this.examp = ".";
    }

    public Word(int id, String word, String mean, String examp) {
        this.id = id;
        this.word = word;
        this.mean = mean;
        this.examp = examp;
    }

    public Word(String word, String mean) {
        this.word = word;
        this.mean = mean;
        this.examp = ".";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getExamp() {
        return examp;
    }

    public void setExamp(String examp) {
        this.examp = examp;
    }
}
