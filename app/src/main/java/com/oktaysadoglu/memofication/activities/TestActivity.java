package com.oktaysadoglu.memofication.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.gameActivity.adapters.SwipeDeckAdapter;
import com.oktaysadoglu.memofication.jobs.TestJob;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_swipe_layout);
        final SwipeDeck cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        final ArrayList<String> testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        SwipeAdapter adapter = new SwipeAdapter(testData, this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }

        });

        Button button = (Button) findViewById(R.id.ex_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(180);
            }
        });

    }


    public class SwipeAdapter extends BaseAdapter {

        private List<String> data;
        private TestActivity context;

        public SwipeAdapter(List<String> data, TestActivity context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if(v == null){
                LayoutInflater inflater = getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.ex_card_view, parent, false);
            }
            ((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = (String)getItem(position);
                    Log.i("MainActivity", item);
                }
            });

            return v;
        }

    }

}
