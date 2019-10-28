package m1sirsoirandroid.triviaquiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import info.hoang8f.widget.FButton;

public class MainGameActivity extends AppCompatActivity {
    FButton buttonA, buttonB, buttonC, buttonD, buttonE;
    TextView questionText, triviaQuizText, timeText, resultText, coinText1, coinText, currentPlayer;
    TriviaQuizHelper triviaQuizHelper;

    TriviaQuestion currentQuestion;
    List<TriviaQuestion> list;
    int qid = 0;
    int timeValue = 20;
    static int CurrentPlayer = 0;
    static int HighScore ;
    HashMap<String,Integer> Highscores;
    int Player1CoinValue = 0;
    int Player2CoinValue = 0;
    int Player1JokerUse = 0;
    int Player2JokerUse = 0;
    int DbHighScore;
    int DbMinHighScore;
    String Winner;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        //Initializing variables
        questionText = findViewById(R.id.triviaQuestion);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        buttonE = findViewById(R.id.buttonE);
        triviaQuizText = findViewById(R.id.triviaQuizText);
        timeText = findViewById(R.id.timeText);
        resultText = findViewById(R.id.resultText);
        coinText1 = findViewById(R.id.coinText1);
        coinText = findViewById(R.id.coinText);
        currentPlayer = findViewById(R.id.currentPlayer);
        CurrentPlayer = 1;
        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);
        coinText1.setTypeface(tb);

        //Our database helper class
        triviaQuizHelper = new TriviaQuizHelper(this);
        //Make db writable
        triviaQuizHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            triviaQuizHelper.allQuestion();
        }

        //This will return us a list of data type TriviaQuestion
        list = triviaQuizHelper.getAllOfTheQuestions();

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list);

        //currentQuestion will hold the que, 4 option and ans for particular id
        currentQuestion = list.get(qid);
        triviaQuizHelper.addHighScore("Default",0);
        Highscores = triviaQuizHelper.getHighScore();

        DbHighScore = Collections.max(Highscores.values());
        DbMinHighScore = Collections.min(Highscores.values());
        //countDownTimer
        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(timeValue + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == 0) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method

                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will update the questions for the other user
                resultText.setText("");
                updateQueAndOptions();
            }
        }.start();

        //This method will set the que and four options
        updateQueAndOptionsInit();


    }

    public void updateQueAndOptionsInit(){
        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());
        String player = "player"+CurrentPlayer;
        currentPlayer.setText(player);

        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();
        Player1CoinValue=0;
        Player2CoinValue=0;
    }


    public void updateQueAndOptions() {

        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());

        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        if(CurrentPlayer==1){
            CurrentPlayer++;
            Player1CoinValue+=2;
        //set the value of coin text
        coinText1.setText(String.valueOf(Player1CoinValue));
        //Now since user has ans correct increment the coinvalue



        }
        else {
            CurrentPlayer--;
            Player2CoinValue+=2;
            coinText.setText(String.valueOf(Player2CoinValue));


        }
        String player = "player"+CurrentPlayer;
        currentPlayer.setText(player);

    }
    public void falseQueAndOptions() {

        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());


        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();
        if(CurrentPlayer==1){
            CurrentPlayer++;



        }
        else {
            CurrentPlayer--;


        }
        String player = "player"+CurrentPlayer;
        currentPlayer.setText(player);

    }
    public void updateJokerAndOptions() {


        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());

        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        if(CurrentPlayer==1){
            ++Player1JokerUse;
            CurrentPlayer++;
            ++Player1CoinValue;
            //set the value of coin text
            coinText1.setText(String.valueOf(Player1CoinValue));
            //Now since user has ans correct increment the coinvalue



        }
        else{
            ++Player2JokerUse;
            CurrentPlayer--;
            ++Player2CoinValue;
            coinText.setText(String.valueOf(Player2CoinValue));




        }
        String player = "player"+CurrentPlayer;
        currentPlayer.setText(player);
    }
    //Onclick listener for first button
    public void buttonA(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < 11) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();

                //Show the dialog that ans is correct
                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }else {
            if (qid < 11){
            qid++;
            //get the que and 4 option and store in the currentQuestion
            currentQuestion = list.get(qid);
            //Now this method will set the new que and 4 options
            falseQueAndOptions();
            //reset the color of buttons back to white
            resetColor();
            //Enable button - remember we had disable them when user ans was correct in there particular button methods
            if(CurrentPlayer==1&&Player1JokerUse==0)
                enableButtons();
            else enableButton();
            if(CurrentPlayer==2&&Player2JokerUse==0)
                enableButtons();
            else enableButton();}
        else {
            if(Player1CoinValue>Player2CoinValue){
                Winner = "player 1";
                HighScore = Player1CoinValue;
            }
            else{
                Winner = "player 2";
                HighScore = Player2CoinValue;
            }
            if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                Highscore();
            }
            else {
                gameWon();
            }
        }
    }
        //User ans is wrong then just navigate him to the PlayAgain activity

    }

    //Onclick listener for sec button
    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < 11) {
                disableButton();
                correctDialog();
            }       else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }else {
            if (qid < 11){
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                falseQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                if(CurrentPlayer==1&&Player1JokerUse==0)
                    enableButtons();
                else enableButton();
                if(CurrentPlayer==2&&Player2JokerUse==0)
                    enableButtons();
                else enableButton();}
            else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }
    }

    //Onclick listener for third button
    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < 11) {
                disableButton();
                correctDialog();
            }       else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }else {
            if (qid < 11){
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                falseQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                if(CurrentPlayer==1&&Player1JokerUse==0)
                    enableButtons();
                else enableButton();
                if(CurrentPlayer==2&&Player2JokerUse==0)
                    enableButtons();
                else enableButton();}
            else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }
    }

    //Onclick listener for fourth button
    public void buttonD(View view) {
        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
            buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < 11) {
                disableButton();
                correctDialog();
            }       else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }else {
            if (qid < 11){
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                falseQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                if(CurrentPlayer==1&&Player1JokerUse==0)
                    enableButtons();
                else enableButton();
                if(CurrentPlayer==2&&Player2JokerUse==0)
                    enableButtons();
                else enableButton();}
            else {
                if(Player1CoinValue>Player2CoinValue){
                    Winner = "player 1";
                    HighScore = Player1CoinValue;
                }
                else{
                    Winner = "player 2";
                    HighScore = Player2CoinValue;
                }
                if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                    Highscore();
                }
                else {
                    gameWon();
                }
            }
        }
    }
    //Onclick listener for fifth button
    public void buttonE(View view) {
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer()))
            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
        else if(currentQuestion.getOptB().equals(currentQuestion.getAnswer()))
            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
        else if(currentQuestion.getOptC().equals(currentQuestion.getAnswer()))
            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
        else buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
        if (qid < 11) {
                disableButton();
                jokerDialog();
        }       else {
            if(Player1CoinValue>Player2CoinValue){
                Winner = "player 1";
                HighScore = Player1CoinValue;
            }
            else{
                Winner = "player 2";
                HighScore = Player2CoinValue;
            }
            if (HighScore>DbHighScore||HighScore>DbMinHighScore){

                Highscore();
            }
            else {
                gameWon();
            }
        }

    }
    //This method will navigate from current activity to GameWon
    public void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }
    public void Highscore(){
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
        finish();
    }


    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    //On BackPressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = dialogCorrect.findViewById(R.id.dialogNext);

        //Setting type faces
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                if(CurrentPlayer==1&&Player1JokerUse<2)
                enableButtons();
                else enableButton();
                if(CurrentPlayer==2&&Player2JokerUse<2)
                    enableButtons();
                else enableButton();
            }
        });
    }
    public void jokerDialog() {
        final Dialog dialogJoker = new Dialog(MainGameActivity.this);
        dialogJoker.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogJoker.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogJoker.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogJoker.setContentView(R.layout.dialog_joker);
        dialogJoker.setCancelable(false);
        dialogJoker.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView jokerText = dialogJoker.findViewById(R.id.jokerText);
        FButton buttonNext = dialogJoker.findViewById(R.id.dialogNext);

        //Setting type faces
        jokerText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CurrentPlayer==1){
                    if(Player2JokerUse<2){
                    //This will dismiss the dialog
                    dialogJoker.dismiss();
                    //it will increment the question number
                    qid++;
                    //get the que and 4 option and store in the currentQuestion
                    currentQuestion = list.get(qid);
                    //Now this method will set the new que and 4 options
                    updateJokerAndOptions();
                    //reset the color of buttons back to white
                    resetColor();
                    //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButtons();}
                else {
                        //This will dismiss the dialog
                        dialogJoker.dismiss();
                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQuestion = list.get(qid);
                        //Now this method will set the new que and 4 options
                        updateJokerAndOptions();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                }
                }
                else{
                    if(Player1JokerUse<2){
                        //This will dismiss the dialog
                        dialogJoker.dismiss();
                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQuestion = list.get(qid);
                        //Now this method will set the new que and 4 options
                        updateJokerAndOptions();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButtons();}
                    else {
                        //This will dismiss the dialog
                        dialogJoker.dismiss();
                        //it will increment the question number
                        qid++;
                        //get the que and 4 option and store in the currentQuestion
                        currentQuestion = list.get(qid);
                        //Now this method will set the new que and 4 options
                        updateJokerAndOptions();
                        //reset the color of buttons back to white
                        resetColor();
                        //Enable button - remember we had disable them when user ans was correct in there particular button methods
                        enableButton();
                    }
                }
            }
        });
    }



    //This method will make button color white again since our one button color was turned green
    public void resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonE.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonE.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }
    public void enableButtons() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
        buttonE.setEnabled(true);
    }

}
