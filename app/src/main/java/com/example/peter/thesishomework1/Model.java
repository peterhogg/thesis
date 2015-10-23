package com.example.peter.thesishomework1;


import java.util.ArrayList;
import java.util.Map;

public class Model{
    ArrayList<MyListener> listeners;
    ArrayList<String> pollQuestions;
    Map <String, Integer>poll;

    public void vote(String s){
        int votes = poll.get(s);
        votes ++;
        poll.put(s,votes);


    }

    public void add(String s){
        int votes = 0;
        pollQuestions.add(pollQuestions.size(),s);
        poll.put(s,votes);

    }

    public int size(){
        return poll.size();
    }

    void addChangeListener(MyListener l){
        this.listeners.add(l);

    }

    void notifyChange(){
        for (MyListener l:this.listeners) {
            l.changed();
        }
    }


}