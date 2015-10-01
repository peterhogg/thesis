package com.example.peter.thesishomework1;

/**
 * Created by peter on 25/09/15.
 *
 */
public class Model {
    private int aClicks;
    private int bClicks;

    Model(){
        this.aClicks = 0;
        this.bClicks = 0;
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

}
