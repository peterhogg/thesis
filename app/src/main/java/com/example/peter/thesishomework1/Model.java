package com.example.peter.thesishomework1;


import android.app.Activity;
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
    String uuid;

    public Model(Socket s){
        listeners = new ArrayList<>();
        //String url = "https://cryptic-brushlands-8704.herokuapp.com";
        String url = "http://192.168.119.233:5000";
        try {
            s = IO.socket(url);
        } catch (URISyntaxException e) {
            Log.e("Connection Error", "Socket was unable to connect");
            e.printStackTrace();
        }
        //Adds the callback funtions for socket events
        s.on("topic", topic);
        s.on("id",id);

        s.connect();

        this.socket = s;


        pollQuestions = new ArrayList<>();
        poll = new HashMap<>();
    }
    public void addListener(MyListener l){
        listeners.add(listeners.size(), l);
    }

    public void understand(String s, int v){
        JSONObject data = new JSONObject();
        try {
            data.put("name",s);
            data.put("value",v);
            data.put("id",uuid);
        }catch (JSONException e){
            e.printStackTrace();
        }
        this.socket.emit("understand", data);
    }
    public void like(String s){
        JSONObject data = new JSONObject();
        try {
            data.put("name",s);
            data.put("id",uuid);
        }catch (JSONException e){
            e.printStackTrace();
        }
        this.socket.emit("like", data);
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

    final private Emitter.Listener topic = new Emitter.Listener(){
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.d("JSON",data.toString());
            try {
                socket.emit("recevied", "");
                //Add the new topic to the view
                Log.d("this", this.toString());
                add(data.getString("name"));

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    //Assigns a UUID for the client
    final private Emitter.Listener id = new Emitter.Listener(){
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.d("JSON",data.toString());
            try {
                uuid = data.getString("id");
                Log.d("ID",uuid);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

}