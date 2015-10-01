package com.example.peter.thesishomework1;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by peter on 30/09/15.
 */
public class WebModel {
    public class Model {
        private int aClicks;
        private int bClicks;
        private Socket mSocket;
        {
            try {
                mSocket = IO.socket("http://localhost:8080");
            } catch (URISyntaxException e) {}
        }
        public Model(){
            mSocket.connect();
            mSocket.emit("get", "a");
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
}
