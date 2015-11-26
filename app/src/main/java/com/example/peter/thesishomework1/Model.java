package com.example.peter.thesishomework1;


import android.util.Log;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class Model{
    ArrayList<String> pollQuestions;
    HashMap<String, Integer> poll;
    ArrayList<MyListener> listeners;
    Socket socket;

    public Model(Socket s){
        listeners = new ArrayList<>();
        //String url = "//cryptic-brushlands-8704.herokuapp.com";
        String url = "http://192.168.119.233:5000";
        try {
            s = IO.socket(url);
        } catch (URISyntaxException e) {
            Log.e("Connection Error", "Socket was unable to connect");
            e.printStackTrace();
        }
        s.on("newVote", newVote);
        s.on("topicActivated", topicActivated);
        s.connect();

        this.socket = s;


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
        JSONObject data = new JSONObject();
        try {
            data.put("index",0);
        }catch (JSONException e){
            e.printStackTrace();
        }

        this.socket.emit("vote", data);
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
            Log.d("Incoming Socket Message", "A voted message has been sent out from the server");
            //notifyChange();
        }
    };
    private Emitter.Listener topicActivated = new Emitter.Listener(){
        public void call(final Object... args) {
            Log.d("Incoming Socket Message", "Topic Changed");

            JSONObject data = (JSONObject) args[0];
            try {
                Log.d("data",data.getString("topic"));
                //m.add(data.getString("topic"));
                
            }
            catch (Exception e){
                e.printStackTrace();
            }

            //notifyChange();
        }
    };

}