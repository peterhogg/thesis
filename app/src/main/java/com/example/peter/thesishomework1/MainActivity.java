package com.example.peter.thesishomework1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a model class
        model = new Model();


        //Creates event handler for button clicks
        Button aButton = (Button) findViewById(R.id.a_button);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = model.getaClicks();
                a++;
                model.setaClicks(a);
                updateACounter();
            }
        });

        Button bButton = (Button) findViewById(R.id.b_button);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = model.getbClicks();
                b++;
                model.setbClicks(b);
                updateBCounter();
            }
        });
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
    public void updateACounter(){
        int a = model.getaClicks();
        TextView aTextview = (TextView) findViewById(R.id.a_text_view);
        aTextview.setText(Integer.toString(a));



    }
    public void updateBCounter(){
        int b = model.getbClicks();
        TextView bTextview = (TextView) findViewById(R.id.bTextView);
        bTextview.setText(Integer.toString(b));

    }
}
