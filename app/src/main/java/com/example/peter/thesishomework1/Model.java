package com.example.peter.thesishomework1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model{
    ArrayList<MyListener> listeners;
    ArrayList<String> pollQuestions;
    HashMap<String, Integer> poll;

    public Model(){
        //listeners = new ArrayList<>();
        pollQuestions = new ArrayList<>();
        poll = new HashMap<>();
    }

    public void vote(String s){
        int votes = poll.get(s);
        votes ++;
        poll.put(s,votes);


    }

    public void add(String s){
        int votes = 0;
        pollQuestions.add(this.size(),s);
        poll.put(s, votes);

    }

    public int size(){
        if(this.pollQuestions == null){
            return 0;
        }
        else{
            return this.pollQuestions.size();
        }
    }

    void addChangeListener(MyListener l){
        this.listeners.add(l);

    }

    void notifyChange(){
        for (MyListener l:this.listeners) {
            l.changed();
        }
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