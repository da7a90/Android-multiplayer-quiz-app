package m1sirsoirandroid.triviaquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame,quit,highscore;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //the below method will initialize views
        initViews();

        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //highscores button - it will show you the best five scores unordered
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,HighScores.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        //initialize views here
        playGame = findViewById(R.id.playGame);
        quit = findViewById(R.id.quit);
        tQ = findViewById(R.id.tQ);
        highscore = findViewById(R.id.highscore);

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
        highscore.setTypeface(typeface);
    }
}
