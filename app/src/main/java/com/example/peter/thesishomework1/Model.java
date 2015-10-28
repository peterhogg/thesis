package com.example.peter.thesishomework1;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class Model{
    ArrayList<String> pollQuestions;
    HashMap<String, Integer> poll;
    MyListener listener;

    public Model(){
        //listeners = new ArrayList<>();
        pollQuestions = new ArrayList<>();
        poll = new HashMap<>();
    }
    public void addListener(MyListener l){
        this.listener = l;
    }

    public void vote(String s){
        int votes = poll.get(s);
        votes ++;
        poll.put(s,votes);
        Log.d("voted", s + ": " + votes);
        notifyChange();



    }

    public void add(String s){
        int votes = 0;
        pollQuestions.add(this.size(),s);
        poll.put(s, votes);
        notifyChange();

    }

    public int size(){
        if(this.pollQuestions == null){
            return 0;
        }
        else{
            return this.pollQuestions.size();
        }
    }

    void notifyChange(){
        listener.changed();
    }
    public Object getItem(int i){
        if(this.pollQuestions == null){
            return 0;
        }
        else{
            return this.pollQuestions.get(i);
        }

    }

}