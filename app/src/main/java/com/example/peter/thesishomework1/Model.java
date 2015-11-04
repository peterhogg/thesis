package com.example.peter.thesishomework1;


import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class Model{
    ArrayList<String> pollQuestions;
    HashMap<String, Integer> poll;
    ArrayList<MyListener> listeners;
    Socket socket;

    public Model(Socket s){
        listeners = new ArrayList<>();
        this.socket = s;
        this.socket.connect();
        this.socket.on("newVote",newVote);

        pollQuestions = new ArrayList<>();
        poll = new HashMap<>();
    }
    public void addListener(MyListener l){
        listeners.add(listeners.size(), l);
    }

    public void vote(String s){
        int votes = poll.get(s);
        votes ++;
        poll.put(s,votes);
        Log.d("voted", s + ": " + votes);
        //TODO: remove the above code, and handle it server side
        //Send a vote over the socket to the server

        this.socket.emit("vote", s);
        //Notify view that a change has occured, and redraw the listview
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
        for (MyListener l:listeners) {
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
    //Renews the view when a vote is received
    private Emitter.Listener newVote = new Emitter.Listener(){
        public void call(final Object... args) {
            notifyChange();
        }
    };

}