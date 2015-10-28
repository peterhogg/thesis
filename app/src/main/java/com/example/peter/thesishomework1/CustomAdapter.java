package com.example.peter.thesishomework1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


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
        final TextView pollQuestion;
        TextView myVotes;
        TextView globalVotes;
        Button voteButton;

        //Grabs the information from the model
        final String pollOption = myModel.pollQuestions.get(i);
        int votes = myModel.poll.get(pollOption);


        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.row_layout,viewGroup,false);


        //Sets the text in the view
        pollQuestion = (TextView) view.findViewById(R.id.pollQuestion);
        pollQuestion.setText(pollOption);

        myVotes = (TextView) view.findViewById(R.id.myVotes);
        myVotes.setText(Integer.toString(votes));

        voteButton = (Button) view.findViewById(R.id.voteButton);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myModel.vote(pollOption);
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
