package com.example.peter.thesishomework1;


import java.util.ArrayList;

public class Model{
    ArrayList<MyListener> listeners;
    public void vote(int i){
    }

    public void add(String q){
    }

    public int size(){
        return listeners.size();
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