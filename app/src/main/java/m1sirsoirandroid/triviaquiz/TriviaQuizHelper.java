package m1sirsoirandroid.triviaquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class TriviaQuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //high score
    private static final String HIGH_SCORE_TABLE = "HS";
    //player name
    private static final String PLAYER_NAME = "PLAYER_NAME";
    //score
    private static final String SCORE ="SCORE";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    //create highscore table
    private static final String HIGH_SCORE = "CREATE TABLE " + HIGH_SCORE_TABLE + "("+PLAYER_NAME +" VARCHAR(255),"+SCORE +" INTEGER);";
    //drop highscore table
    private static final String DROP_HIGH_SCORE = "DROP TABLE IF EXISTS" + HIGH_SCORE_TABLE;
    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(HIGH_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion("qui est le meilleur developpeur android?", "dan larrimer", "cristiano ronaldo", "macky sall", "sidi ahmed", "sidi ahmed"));

        arraylist.add(new TriviaQuestion("qui est le responsable de M1 sir soir a l'ucad?", "modou gueye", "sidi ahmed", "bill gates", "mahatma gandhi", "modou gueye"));

        arraylist.add(new TriviaQuestion("qui est le president du senegal ?", "che guevara", "dieudonne mbala", "macky sall", "batman", "macky sall"));

        arraylist.add(new TriviaQuestion("quel est le meilleur operateur mobile au senegal ?", "orange", "vodafone", "Nasa", "free", "free"));

        arraylist.add(new TriviaQuestion("qui est le meilleur joueur de football?", "sadio mane", "mohamed salah", "michael jackson", "shakira", "sadio mane"));

        arraylist.add(new TriviaQuestion("qui a inventé le microphone?", "alexander graham-bell", "recep teyib erdogan", "ben affleck", "Sam harris", "alexander graham-bell"));

        arraylist.add(new TriviaQuestion("qui a creer facebook ?", "mark zuckerburg", "gallileo", "ronald mcdonald", "burger king", "mark zuckerburg"));

        arraylist.add(new TriviaQuestion("quelle est la molecule d'eau?", "N2O", "THC", "HCOONA", "H2O", "H2O"));

        arraylist.add(new TriviaQuestion("qui est l'homme le plus riche du monde?", "sidi ahmed", "bill gates", "dalay lama", "macky sall", "bill gates"));

        arraylist.add(new TriviaQuestion("le sucre est-il bon pour la santé?", "oui", "non", "quel sucre?", "je prefere le sel", "non"));

        arraylist.add(new TriviaQuestion("lequel est un reseau social actif parmi les choix?", "myspace", "Digg", "twitter", "la fouine", "twitter"));

        arraylist.add(new TriviaQuestion("qui a ecrit la fameuse formule E=mc^2", "Albert Einstein", "Galilleo", "picasso", "Bill Gates", "Albert Einstein"));

        arraylist.add(new TriviaQuestion("qui est le president du rwanda?", "Donald Trump", "macky sall", "paul kagame", "mohammed vi", "paul kagame"));

        arraylist.add(new TriviaQuestion("qui a fondé Microsoft?", "Bill Gates", "Bill Clinton", "michael jackson", "Steve jobs", "Bill Gates"));

        arraylist.add(new TriviaQuestion("qui a fondé Apple ?", "Steve Jobs", "Steve Washinton", "Bill Gates", "Jobs Wills", "Steve Jobs"));

        arraylist.add(new TriviaQuestion("qui a fondé Google ?", "Steve Jobs", "Bill Gates", "Larry Page", "sadio mane", "Larry Page"));

        arraylist.add(new TriviaQuestion("qui est donald trump?", "un clown", "c'est mon pote", "president des usa", "donald quoi?", "president des usa"));

        arraylist.add(new TriviaQuestion("qui a eu le ballon d'or en 2015 ?", "Lionel Messi", "Cristiano Ronaldo", "Neymar", "Kaka", "Lionel Messi"));

        arraylist.add(new TriviaQuestion("qui a eu le ballon d'or en 2014 ?", "Neymar", "Lionel Messi", "Cristiano Ronaldo", "Kaka", "Cristiano Ronaldo"));

        arraylist.add(new TriviaQuestion("qui est le fondateur de steam ?", "Bill Cliton", "Bill Williams", "Gabe Newell", "Bill Gates", "Gabe Newell"));

        arraylist.add(new TriviaQuestion("quand est ce que ca neige au senegal ?", "janvier", "aout mais en casamance", "jamais", "c'est quoi la neige?", "jamais"));

        arraylist.add(new TriviaQuestion("quel est le premier president du senegal ?", "abraham lincoln", "bill cosby", "Leopold sedar senghor", "aristotle", "Leopold sedar senghor"));

        arraylist.add(new TriviaQuestion("qui est michael schumacher ?", "un conducteur F1", "on a fait le primaire ensemble", "no idea", "c'est italien ca?", "un conducteur F1"));

        arraylist.add(new TriviaQuestion("combien de planetes y'a t'il dans notre systeme solaire?", "9", "8", "100000", "24", "8"));

        arraylist.add(new TriviaQuestion("question bonus 2+2?", "7", "4", "5", "38", "4"));

        arraylist.add(new TriviaQuestion("quel est le plus grand pays du monde en terme de superficie?", "senegal", "russie", "seychelles", "l'ocean atlantique", "la russie"));



        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    void addHighScore(String playerName,int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(PLAYER_NAME, playerName);
            values.put(SCORE, score);
            db.insert(HIGH_SCORE_TABLE, null, values);
            db.delete(HIGH_SCORE_TABLE,  PLAYER_NAME +" NOT IN "+
                            " (SELECT "+PLAYER_NAME+" FROM " + HIGH_SCORE_TABLE + " ORDER BY "+ SCORE +" DESC LIMIT 5)",
                    null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }
    HashMap<String,Integer> getHighScore(){
    HashMap<String,Integer> HighScore = new HashMap<>();
    SQLiteDatabase db = this.getReadableDatabase();
    db.beginTransaction();
    Cursor resultset = db.rawQuery("SELECT * FROM "+HIGH_SCORE_TABLE+" LIMIT 5 ",null);
    while (resultset.moveToNext()){
        HighScore.put(resultset.getString(0),resultset.getInt(1));
    }
        db.setTransactionSuccessful();
        db.endTransaction();
        resultset.close();
        db.close();
    return HighScore;
    }

    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String[] column = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, column, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
