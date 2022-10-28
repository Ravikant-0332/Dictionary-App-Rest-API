package com.example.dictionaryapp.models;

import java.io.Serializable;

public class Phonetic implements Serializable {
    String text="";
    String audio="";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
