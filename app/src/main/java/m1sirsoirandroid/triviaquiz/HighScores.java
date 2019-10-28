package m1sirsoirandroid.triviaquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.hoang8f.widget.FButton;

public class HighScores extends AppCompatActivity {

    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    TextView textview7;
    TextView textview8;
    TextView textview9;
    TextView textview10;
    HashMap<TextView,TextView> map;
    HashMap<String,Integer> HighScores;
    TriviaQuizHelper triviaQuizHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);
        //the below method will initialize views
        textview1 = findViewById(R.id.textView1);
        textview2 = findViewById(R.id.textView2);
        textview3 = findViewById(R.id.textView3);
        textview4 = findViewById(R.id.textView4);
        textview5 = findViewById(R.id.textView5);
        textview6 = findViewById(R.id.textView6);
        textview7 = findViewById(R.id.textView7);
        textview8 = findViewById(R.id.textView8);
        textview9 = findViewById(R.id.textView9);
        textview10 = findViewById(R.id.textView10);

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        textview1.setTypeface(typeface);
        textview2.setTypeface(typeface);
        textview3.setTypeface(typeface);
        textview4.setTypeface(typeface);
        textview5.setTypeface(typeface);
        textview6.setTypeface(typeface);
        textview7.setTypeface(typeface);
        textview8.setTypeface(typeface);
        textview9.setTypeface(typeface);
        textview10.setTypeface(typeface);
        map = new HashMap<>();
        map.put(textview1,textview2);
        map.put(textview3,textview4);
        map.put(textview5,textview6);
        map.put(textview7,textview8);
        map.put(textview9,textview10);
        triviaQuizHelper = new TriviaQuizHelper(this);
        triviaQuizHelper.getWritableDatabase();

        HighScores= triviaQuizHelper.getHighScore();

        ArrayList<String> t1 = new ArrayList<>(HighScores.keySet()) ;
        ArrayList<Integer> t2 =new ArrayList<>(HighScores.values()) ;
        int i = 0;
        for(Map.Entry<TextView,TextView> entry: map.entrySet()){
            entry.getKey().setText(t1.get(i));
            entry.getValue().setText(String.valueOf(t2.get(i)));
            if(t2.size()==i+1)
                break;
            i++;
        }


    }

    public void HomeScreen(View view) {
        Intent intent = new Intent(HighScores.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }
}
