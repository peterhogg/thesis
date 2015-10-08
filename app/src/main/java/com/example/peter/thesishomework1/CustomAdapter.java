package com.example.peter.thesishomework1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by peter on 07/10/15.
 */
public class CustomAdapter extends BaseAdapter {
    private String[] pollQuestions;
    private Context context;

    public CustomAdapter(Context context, String[] pollQuestions){
        this.context = context;
        this.pollQuestions = pollQuestions;
    }
    @Override
    public int getCount() {
        return pollQuestions.length;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView pollQuestion;
        TextView myVotes;
        TextView globalVotes;
        Button voteButton;


        if (view == null) {
            // there is no object to reuse, create one
            pollQuestion = new TextView(context);
            pollQuestion.setText(this.pollQuestions[i]);
        } else {
            // we're reusing an old object
            pollQuestion = (TextView)view;
        }

        return pollQuestion;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return pollQuestions[i];
    }
}