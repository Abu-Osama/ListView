package com.techpalle.b33_listviewslow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //DECLARE ALL VARIABLES
    EditText et1, et2;
    Button b;
    ListView lv;
    ArrayList<Movies> al;
    MyAdapter myAdapter;
    int count = 0; //for tracking serial numbers in the listview

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return al.size();
        }
        @Override
        public Object getItem(int i) {
            return al.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //load row.xml and all views
            Log.d("ADAPTER","POSITION - "+i);
            View v = getLayoutInflater().inflate(R.layout.row, null);
            TextView tv1 = (TextView) v.findViewById(R.id.textView1);
            TextView tv2 = (TextView) v.findViewById(R.id.textView2);
            TextView tv3 = (TextView) v.findViewById(R.id.textView3);
            //read data from arraylist based on the position
            Movies movies = al.get(i); //reading movies object
            //apply data - using GETTERS
            tv1.setText(movies.getSno());
            tv2.setText(movies.getActor());
            tv3.setText(movies.getMovie());
            //and return row.xml
            return v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize all variables
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        b = (Button) findViewById(R.id.button1);
        lv = (ListView) findViewById(R.id.listView1);
        al = new ArrayList<Movies>();
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        //button click listener to insert movies into listview
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //READ DATA FROM EDIT TEXT
                count++; //increment serial number
                String actor = et1.getText().toString();
                String movie = et2.getText().toString();
                //create an empty movies object
                Movies mymovie = new Movies();
                //INSERT DATA INTO MOVIES OBJECT USING - SETTERS
                mymovie.setSno(""+count);
                mymovie.setActor(actor);
                mymovie.setMovie(movie);
                //ADD THIS MOVIE OBJECT TO ARRAYLIST
                al.add(mymovie);
                //TELL TO ADAPTER
                myAdapter.notifyDataSetChanged();
                //clean edit text fields and put cursor on first edit text
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
            }
        });
    }
}
