package m1sirsoirandroid.triviaquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameWon extends Activity {
    TextView Winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        Winner = findViewById(R.id.winner);
        Winner.setText("Player "+MainGameActivity.CurrentPlayer+" Wins!!");
    }

    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    public void PlayAgain(View view) {
        Intent intent = new Intent(GameWon.this, MainGameActivity.class);
        startActivity(intent);
        finish();
    }
    public void HomeScreen(View view) {
        Intent intent = new Intent(GameWon.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }
}
