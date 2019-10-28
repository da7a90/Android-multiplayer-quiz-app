package m1sirsoirandroid.triviaquiz;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.*;

public class HighScore extends Activity {
    TriviaQuizHelper triviaQuizHelper;
    Button clickButton;
    TextInputEditText PlayerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);
         clickButton = findViewById(R.id.button);
        triviaQuizHelper = new TriviaQuizHelper(this);
        triviaQuizHelper.getWritableDatabase();
        PlayerName = findViewById(R.id.hsfield);

        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                triviaQuizHelper.addHighScore(String.valueOf(PlayerName.getText()), MainGameActivity.HighScore);
                GameWon();
            }
        });
    }

    //This is onclick listener for button
    //it will navigate from this activity to GameWon
    public void GameWon() {
        Intent intent = new Intent(HighScore.this, GameWon.class);
        startActivity(intent);
        finish();
    }
}
