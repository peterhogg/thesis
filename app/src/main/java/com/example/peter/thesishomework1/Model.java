package com.example.peter.thesishomework1;

/**
 * Created by peter on 25/09/15.
 *
 */
public class Model {
    private int aClicks;
    private int bClicks;
    private String[] pollQuestions;

    Model(){
        this.aClicks = 0;
        this.bClicks = 0;
        this.pollQuestions = new String[]{"a","b"};
    }

    //Getter and setter for aClicks
    public void setaClicks(int a){
        this.aClicks = a;
    }
    public int getaClicks(){
        return this.aClicks;
    }

    //Getter and setter for bClicks
    public void setbClicks(int b){
        this.bClicks = b;
    }
    public int getbClicks(){
        return this.bClicks;
    }

    //Getter and setter for pollQuestions
    public void setPollQuestions(String[] pollQuestions){
        this.pollQuestions = pollQuestions;
    }
    public String[] getPollQuestions(){
        return pollQuestions;
    }

}
