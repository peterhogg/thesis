package com.example.peter.thesishomework1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;


/**
 * Created by peter on 07/10/15.
 */
public class CustomAdapter extends BaseAdapter {
    private Model myModel;
    private Context context;

    public CustomAdapter(Context context, Model myModel ){
        this.context = context;
        this.myModel = myModel;
    }
    @Override
    public int getCount() {
        return myModel.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CustomAdapter adapter = this;

        //Creates the view objects
        final TextView topicLbl;
        Button understandButton;
        DiscreteSeekBar understandBar;
        Button likeButton;

        //Grabs the information from the model
        final String topic = myModel.pollQuestions.get(i);


        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.row_layout,viewGroup,false);


        //Sets the text in the view
        topicLbl = (TextView) view.findViewById(R.id.pollQuestion);
        topicLbl.setText(topic);

        /*understandButton = (Button) view.findViewById(R.id.understandButton);
        understandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myModel.understand(topic);
            }
        });
        */
        understandBar =  (DiscreteSeekBar) view.findViewById(R.id.understandBar);
        understandBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                int value = seekBar.getProgress();
                myModel.understand(topic, value);
            }
        });
        int heartUnicode = 0x2764;
        char[] heart = Character.toChars(heartUnicode);
        likeButton = (Button) view.findViewById(R.id.likeButton);
        likeButton.setText(heart,0,heart.length);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myModel.like(topic);
            }
        });
        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return myModel.getItem(i);
    }
}
