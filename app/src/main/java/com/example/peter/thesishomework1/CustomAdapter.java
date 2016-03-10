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
        final TextView descriptionLlb;
        Button likeButton;
        TextView understandLbl;
        DiscreteSeekBar understandBar;



        //Grabs the information from the model
        Topic currentTopic = myModel.topics.get(i);
        final String topic = currentTopic.title;
        final String description = currentTopic.description;
        Boolean interesting = currentTopic.interesting;
        Boolean difficulty = currentTopic.difficulty;
        int maxDifficulty = 10;
        int minDifficulty = 0;
        if (difficulty){
            maxDifficulty = currentTopic.maxDifficulty;
            minDifficulty = currentTopic.minDifficulty;
        }



        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.row_layout,viewGroup,false);


        //Sets the topic text in the view
        topicLbl = (TextView) view.findViewById(R.id.topic);
        topicLbl.setText(topic);

        //Sets the description text in the view
        descriptionLlb = (TextView) view.findViewById(R.id.description);
        descriptionLlb.setText(description);

        //Set the like button
        int heartUnicode = 0x2764;
        char[] heart = Character.toChars(heartUnicode);
        likeButton = (Button) view.findViewById(R.id.likeButton);
        if (interesting){
            likeButton.setText(heart,0,heart.length);
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myModel.like(topic);
                }
            });
        }else {
            likeButton.setVisibility(View.GONE);
        }


        //Sets the understand bar
        understandBar = (DiscreteSeekBar) view.findViewById(R.id.understandBar);
        understandLbl = (TextView) view.findViewById(R.id.understandLbl);
        if(difficulty) {
            understandBar.setMax(maxDifficulty);
            understandBar.setMin(minDifficulty);
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
        }else{
            understandBar.setVisibility(View.GONE);
            understandLbl.setVisibility(View.GONE);
        }

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
