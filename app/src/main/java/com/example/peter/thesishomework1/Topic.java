package com.example.peter.thesishomework1;

import java.util.ArrayList;

/**
 * Created by peter on 09/03/16.
 */
public class Topic {
    String title;
    String description;
    Boolean interesting;
    Boolean difficulty;
    int minDifficulty;
    int maxDifficulty;
    Boolean question;
    String questionType;
    String questionText;
    ArrayList<Answer> answers;

    public Topic(String title, String description, Boolean interesting, Boolean difficulty, Boolean question ){
        this.title = title;
        this.description = description;
        this.interesting = interesting;
        this.difficulty = difficulty;
        this.question = question;

        answers = new ArrayList<>();

    }
    public Topic(String title){
        this.title =title;
    }


}
