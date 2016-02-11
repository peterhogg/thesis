package com.example.peter.thesishomework1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class MainActivity extends Activity implements MyListener{

    private Socket s;
    private Model model;
    private CustomAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create a model class
        model = new Model(s);
        model.addListener(this);


        //Creates the adapter
        adapter = new CustomAdapter(this,model);

        //Populate the list view
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, model));

        model.add("test");

    }

    @Override
    protected void onStop(){
        super.onStop();
        this.s.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changed(){
        //invalidate the list view so that it refreshes
        //
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View view = findViewById(R.id.listView);
                view.invalidate();
                listView.invalidateViews();
            }
        });

        //adapter.notifyDataSetChanged();

    }


}
